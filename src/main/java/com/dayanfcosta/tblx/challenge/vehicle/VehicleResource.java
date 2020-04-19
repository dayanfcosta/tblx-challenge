package com.dayanfcosta.tblx.challenge.vehicle;

import java.time.LocalDate;
import java.util.Set;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/vehicles")
class VehicleResource {

  private final VehicleService service;

  VehicleResource(final VehicleService service) {
    this.service = service;
  }

  Mono<Set<Integer>> findAll(@RequestParam @DateTimeFormat(iso = ISO.DATE) final LocalDate startTime,
      @RequestParam @DateTimeFormat(iso = ISO.DATE) final LocalDate endTime,
      @RequestParam final String operator) {
    return Mono.just(service.findAll(startTime, endTime, operator));
  }
}
