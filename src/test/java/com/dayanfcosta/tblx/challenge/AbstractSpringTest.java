package com.dayanfcosta.tblx.challenge;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataMongoTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class AbstractSpringTest {

  @Autowired
  private MongoTemplate mongoTemplate;

  @AfterEach
  void tearDown() {
    mongoTemplate.dropCollection("gps-data");
  }

  protected MongoTemplate getMongoTemplate() {
    return mongoTemplate;
  }

}