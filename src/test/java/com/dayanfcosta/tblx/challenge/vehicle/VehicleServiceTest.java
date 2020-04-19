package com.dayanfcosta.tblx.challenge.vehicle;

import static java.time.LocalDate.now;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class VehicleServiceTest {

  private static final String OPERATOR = "OPERATOR";

  @Mock
  private VehicleRepository repository;

  @InjectMocks
  private VehicleService service;

  @BeforeEach
  void setUp() {
    initMocks(this);
  }

  @Test
  void whenFindAll_returnAllIdsFound() {
    when(repository.findAll(any(), any(), any())).thenReturn(asList(1, 2, 3));

    final Set<Integer> vehicleIds = service.findAll(now(), now().plusDays(2), OPERATOR);

    assertThat(vehicleIds).isNotNull();
    assertThat(vehicleIds).isNotEmpty();
    assertThat(vehicleIds).hasSize(3);
  }

  @Test
  void whenFindAllWithStartTimeAfterEndTime_throwsIllegalArgumentException() {
    assertThatIllegalArgumentException()
        .isThrownBy(() -> service.findAll(now().plusDays(2), now(), OPERATOR))
        .withMessage("End time MUST be after or equals start time");
  }

  @Test
  void whenFindAllWithEmptyOperator_trowsIllegalArgumentException() {
    assertThatIllegalArgumentException()
        .isThrownBy(() -> service.findAll(now(), now().plusDays(2), ""))
        .withMessage("Invalid operator ID");
  }

  @Test
  void whenFindAllWithNullOperator_trowsIllegalArgumentException() {
    assertThatNullPointerException()
        .isThrownBy(() -> service.findAll(now(), now().plusDays(2), null))
        .withMessage("Invalid operator ID");
  }
}