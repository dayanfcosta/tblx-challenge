package com.dayanfcosta.tblx.challenge.trace;

import static com.dayanfcosta.tblx.challenge.shared.Constants.TIME_FRAME_FIELD;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
class TraceRepository {

  private final MongoTemplate template;

  TraceRepository(final MongoTemplate template) {
    this.template = template;
  }

  List<Trace> findAll(final LocalDate startTime, final LocalDate endTime, final int vehicleId, final Pageable pageable) {
    final var timeFrameCriteria = Criteria.where(TIME_FRAME_FIELD)
        .gte(startTime)
        .lte(endTime)
        .and("vehicleId").is(vehicleId);
    final var query = new Query(timeFrameCriteria).with(pageable);
    return template.find(query, Trace.class);
  }
}
