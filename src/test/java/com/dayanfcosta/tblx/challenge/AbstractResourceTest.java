package com.dayanfcosta.tblx.challenge;

import static com.dayanfcosta.tblx.challenge.shared.Constants.GPS_DATA_COLLECTION;
import static java.time.LocalDate.now;
import static java.util.stream.Collectors.toList;

import java.util.stream.IntStream;
import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureWebTestClient
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractResourceTest {

  @Autowired
  private MongoTemplate mongoTemplate;

  @BeforeEach
  public void setUp() {
    insertDocuments();
  }

  @AfterEach
  void tearDown() {
    mongoTemplate.dropCollection(GPS_DATA_COLLECTION);
  }

  private void insertDocuments() {
    final var documents = IntStream.rangeClosed(1, 10).mapToObj(this::createDocument).collect(toList());
    mongoTemplate.insert(documents, GPS_DATA_COLLECTION);
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

