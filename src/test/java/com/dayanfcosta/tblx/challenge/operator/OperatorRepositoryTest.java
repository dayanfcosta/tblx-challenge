package com.dayanfcosta.tblx.challenge.operator;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;

import com.dayanfcosta.tblx.challenge.AbstractRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OperatorRepositoryTest extends AbstractRepositoryTest {

  private static final String OPERATOR = "OPERATOR";

  private OperatorRepository repository;

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
    repository = new OperatorRepository(getMongoTemplate());
  }

  @Test
  void whenFindAll_returnsListWithAllFound() {
    final var operators = repository.findAll(now(), now().plusDays(3));

    assertThat(operators).hasSize(1);
    assertThat(operators).containsOnly(OPERATOR);
  }

}