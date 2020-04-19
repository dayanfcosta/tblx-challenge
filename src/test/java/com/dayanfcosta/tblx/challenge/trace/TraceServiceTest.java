package com.dayanfcosta.tblx.challenge.trace;

import static java.time.LocalDate.now;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Pageable;

class TraceServiceTest {

  private static final int VEHICLE_ID = 1;

  @Mock
  private TraceRepository repository;

  @InjectMocks
  private TraceService service;

  @BeforeEach
  void setUp() {
    initMocks(this);
  }

  @Test
  void whenFindAll_returnsAllTracesFound() {
    when(repository.findAll(any(LocalDate.class), any(LocalDate.class), anyInt(), any(Pageable.class)))
        .thenReturn(asList(mock(Trace.class)));

    final List<Trace> traces = service.findAll(now(), now().plusDays(2), 1, 1, 1000);

    assertThat(traces).isNotNull();
    assertThat(traces).isNotEmpty();
    assertThat(traces).hasSize(1);
  }

  @Test
  void whenFindAllWithStartTimeAfterEndTime_throwsIllegalArgumentException() {
    assertThatIllegalArgumentException()
        .isThrownBy(() -> service.findAll(now().plusDays(2), now(), VEHICLE_ID, 1, 1000))
        .withMessage("End time MUST be after or equals start time");
  }

  @Test
  void whenFindAllWithInvalidVehicleId_throwsIllegalArgumentException() {
    assertThatIllegalArgumentException()
        .isThrownBy(() -> service.findAll(now(), now(), 0, 1, 1000))
        .withMessage("Invalid vehicle ID");
  }
}