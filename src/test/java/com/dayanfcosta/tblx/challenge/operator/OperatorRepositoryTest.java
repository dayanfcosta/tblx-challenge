package com.dayanfcosta.tblx.challenge.operator;

import static java.time.LocalDate.now;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import com.dayanfcosta.tblx.challenge.AbstractSpringTest;
import java.util.stream.IntStream;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OperatorRepositoryTest extends AbstractSpringTest {

  private static final String OPERATOR = "XPTO";

  private OperatorRepository repository;

  @BeforeEach
  void setUp() {
    insertDocuments();

    repository = new OperatorRepository(getMongoTemplate());
  }

  @Test
  void whenFindAll_returnsListWithAllFound() {
    final var operators = repository.findAll(now(), now().plusDays(3));

    assertThat(operators).hasSize(1);
    assertThat(operators).containsOnly(OPERATOR);
  }

  private void insertDocuments() {
    final var documents = IntStream.rangeClosed(1, 10).mapToObj(this::createDocument).collect(toList());
    getMongoTemplate().insert(documents, "gps-data");
  }

  private Document createDocument(final int documentNumber) {
    return new Document()
        .append("timestamp", 1352246396000000L + documentNumber)
        .append("lineId", documentNumber)
        .append("direction", "1")
        .append("journeyPatternId", documentNumber)
        .append("timeFrame", now().plusDays(documentNumber))
        .append("vehicleJourneyId", documentNumber)
        .append("operator", OPERATOR)
        .append("congestion", false)
        .append("longitude", -6.258584)
        .append("latitude", 53.340099)
        .append("delay", 0)
        .append("blockId", documentNumber)
        .append("vehicleId", documentNumber)
        .append("stopId", documentNumber)
        .append("atStop", false);
  }
}