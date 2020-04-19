package com.dayanfcosta.tblx.challenge.operator;

import static com.dayanfcosta.tblx.challenge.shared.Constants.GPS_DATA_COLLECTION;
import static com.dayanfcosta.tblx.challenge.shared.Constants.OPERATOR_FIELD;
import static com.dayanfcosta.tblx.challenge.shared.Constants.TIME_FRAME_FIELD;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
class OperatorRepository {

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
