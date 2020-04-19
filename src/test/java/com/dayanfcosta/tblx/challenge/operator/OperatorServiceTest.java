package com.dayanfcosta.tblx.challenge.operator;

import static java.time.LocalDate.now;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class OperatorServiceTest {

  private static final String OPERATOR_1 = "operator_1";
  private static final String OPERATOR_2 = "operator_2";

  @Mock
  private OperatorRepository repository;

  @InjectMocks
  private OperatorService service;

  @BeforeEach
  void setUp() {
    initMocks(this);
  }

  @Test
  void whenFindAll_returnsSetWithAllOperatorsFound() {
    when(repository.findAll(any(), any())).thenReturn(asList(OPERATOR_1, OPERATOR_2));

    final var operators = service.findAll(now(), now().plusDays(2));

    assertThat(operators).isNotNull();
    assertThat(operators).isNotEmpty();
    assertThat(operators).hasOnlyElementsOfType(String.class);
    assertThat(operators).containsOnly(OPERATOR_1, OPERATOR_2);
  }

  @Test
  void whenFindAllWithEqualStartAndEndTime_returnsSetWithAllOperatorsFound() {
    when(repository.findAll(any(), any())).thenReturn(asList(OPERATOR_1, OPERATOR_2));

    final var operators = service.findAll(now(), now());

    assertThat(operators).isNotNull();
    assertThat(operators).isNotEmpty();
    assertThat(operators).hasOnlyElementsOfType(String.class);
    assertThat(operators).containsOnly(OPERATOR_1, OPERATOR_2);
  }

  @Test
  void whenFindAllWithInvalidTimeFrame_throwsIllegalArgumentException() {
    assertThatIllegalArgumentException()
        .isThrownBy(() -> service.findAll(now().plusDays(2), now()))
        .withMessage("End time MUST be after or equals start time");
  }
}