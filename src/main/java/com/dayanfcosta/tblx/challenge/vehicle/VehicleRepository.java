package com.dayanfcosta.tblx.challenge.vehicle;

import static com.dayanfcosta.tblx.challenge.shared.Constants.GPS_DATA_COLLECTION;
import static com.dayanfcosta.tblx.challenge.shared.Constants.OPERATOR_FIELD;
import static com.dayanfcosta.tblx.challenge.shared.Constants.TIME_FRAME_FIELD;
import static com.dayanfcosta.tblx.challenge.shared.Constants.VEHICLE_FIELD;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
class VehicleRepository {

  private static final String AT_STOP_FIELD = "atStop";
  private final MongoTemplate template;

  VehicleRepository(final MongoTemplate template) {
    this.template = template;
  }

  public List<Integer> findAll(final LocalDate startTime, final LocalDate endTime, final String operatorId, final boolean atStop) {
    final var timeFrameCriteria = Criteria.where(TIME_FRAME_FIELD)
        .gte(startTime)
        .lte(endTime)
        .and(OPERATOR_FIELD).is(operatorId)
        .and(AT_STOP_FIELD).is(atStop);
    final var query = new Query(timeFrameCriteria);
    return template.findDistinct(query, VEHICLE_FIELD, GPS_DATA_COLLECTION, Integer.class);
  }
}
