package com.dayanfcosta.tblx.challenge.trace;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.domain.Pageable.unpaged;

import com.dayanfcosta.tblx.challenge.AbstractRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TraceRepositoryTest extends AbstractRepositoryTest {

  private TraceRepository repository;

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
    repository = new TraceRepository(getMongoTemplate());
  }

  @Test
  void whenFindAll_returnsAllTracesFound() {
    final var traces = repository.findAll(now(), now().plusDays(2), 1, unpaged());

    assertThat(traces).isNotNull();
    assertThat(traces).isNotEmpty();
    assertThat(traces).hasSize(1);
  }
}