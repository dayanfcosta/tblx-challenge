package com.dayanfcosta.tblx.challenge.vehicle;

import java.time.LocalDate;
import java.util.Set;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping
  Mono<Set<Integer>> findAll(@RequestParam @DateTimeFormat(iso = ISO.DATE) final LocalDate startTime,
      @RequestParam @DateTimeFormat(iso = ISO.DATE) final LocalDate endTime,
      @RequestParam final String operator,
      @RequestParam(defaultValue = "false", required = false) final boolean atStop) {
    return Mono.just(service.findAll(startTime, endTime, operator, atStop));
  }
}
