package com.dayanfcosta.tblx.challenge.operator;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
class OperatorRepository {

  private static final String OPERATOR_FIELD = "operator";
  private static final String TIME_FRAME_FIELD = "timeFrame";
  private static final String GPS_DATA_COLLECTION = "gps-data";

  private final MongoTemplate template;

  OperatorRepository(final MongoTemplate template) {
    this.template = template;
  }

  List<String> findAll(final LocalDate startTime, final LocalDate endTime) {
    final var timeFrameCriteria = Criteria.where(TIME_FRAME_FIELD).gte(startTime).lte(endTime);
    final var query = new Query(timeFrameCriteria);
    return template.findDistinct(query, OPERATOR_FIELD, GPS_DATA_COLLECTION, String.class);
  }

}
