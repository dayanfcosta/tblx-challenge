package com.dayanfcosta.tblx.challenge;

import static java.time.LocalDate.now;
import static java.util.stream.Collectors.toList;

import java.util.stream.IntStream;
import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataMongoTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public abstract class AbstractRepositoryTest {

  @Autowired
  private MongoTemplate mongoTemplate;

  @BeforeEach
  public void setUp() {
    insertDocuments();
  }

  @AfterEach
  void tearDown() {
    mongoTemplate.dropCollection("gps-data");
  }

  protected MongoTemplate getMongoTemplate() {
    return mongoTemplate;
  }

  private void insertDocuments() {
    final var documents = IntStream.rangeClosed(0, 10).mapToObj(this::createDocument).collect(toList());
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
        .append("operator", "OPERATOR")
        .append("congestion", false)
        .append("longitude", -6.258584)
        .append("latitude", 53.340099)
        .append("delay", 0)
        .append("blockId", documentNumber)
        .append("vehicleId", documentNumber)
        .append("stopId", documentNumber)
        .append("atStop", documentNumber % 2 == 0);
  }

}