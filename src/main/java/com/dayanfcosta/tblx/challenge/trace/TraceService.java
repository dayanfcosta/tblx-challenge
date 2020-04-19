package com.dayanfcosta.tblx.challenge.trace;

import java.time.LocalDate;
import java.util.List;
import org.apache.commons.lang3.Validate;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
class TraceService {

  private final TraceRepository repository;

  TraceService(final TraceRepository repository) {
    this.repository = repository;
  }

  List<Trace> findAll(final LocalDate startTime, final LocalDate endTime, final int vehicleId, final int page, final int size) {
    Validate.isTrue(!startTime.isAfter(endTime), "End time MUST be after or equals start time");
    Validate.isTrue(vehicleId > 0, "Invalid vehicle ID");
    final PageRequest pageRequest = PageRequest.of(page, size, Sort.by("timestamp").ascending());
    return repository.findAll(startTime, endTime, vehicleId, pageRequest);
  }
}
