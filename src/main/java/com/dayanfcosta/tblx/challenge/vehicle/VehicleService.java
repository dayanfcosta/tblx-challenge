package com.dayanfcosta.tblx.challenge.vehicle;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

@Service
class VehicleService {

  private final VehicleRepository repository;

  VehicleService(final VehicleRepository repository) {
    this.repository = repository;
  }

  Set<Integer> findAll(final LocalDate startTime, final LocalDate endTime, final String operatorId) {
    Validate.isTrue(!startTime.isAfter(endTime), "End time MUST be after or equals start time");
    Validate.notBlank(operatorId, "Invalid operator ID");
    final List<Integer> vehicleIds = repository.findAll(startTime, endTime, operatorId);
    return new HashSet<>(vehicleIds);
  }
}
