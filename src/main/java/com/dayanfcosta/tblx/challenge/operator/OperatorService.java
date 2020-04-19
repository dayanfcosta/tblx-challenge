package com.dayanfcosta.tblx.challenge.operator;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

@Service
class OperatorService {

  private final OperatorRepository repository;

  OperatorService(final OperatorRepository repository) {
    this.repository = repository;
  }

  Set<String> findAll(final LocalDate startTime, final LocalDate endTime) {
    Validate.isTrue(!startTime.isAfter(endTime), "End time MUST be after or equals start time");
    final List<String> operators = repository.findAll(startTime, endTime);
    return new HashSet<>(operators);
  }
}
