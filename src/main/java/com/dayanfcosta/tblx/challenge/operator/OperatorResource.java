package com.dayanfcosta.tblx.challenge.operator;

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
@RequestMapping("/operators")
class OperatorResource {

  private final OperatorService service;

  OperatorResource(final OperatorService service) {
    this.service = service;
  }

  @GetMapping
  public Mono<Set<String>> find(@RequestParam @DateTimeFormat(iso = ISO.DATE) final LocalDate startTime,
      @RequestParam @DateTimeFormat(iso = ISO.DATE) final LocalDate endTime) {
    return Mono.just(service.findAll(startTime, endTime));
  }

}