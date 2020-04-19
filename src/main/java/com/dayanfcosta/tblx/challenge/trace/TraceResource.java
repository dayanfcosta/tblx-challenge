package com.dayanfcosta.tblx.challenge.trace;

import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/traces")
class TraceResource {

  private final TraceService service;

  TraceResource(final TraceService service) {
    this.service = service;
  }

  Mono<List<Trace>> findAll(@RequestParam @DateTimeFormat(iso = ISO.DATE) final LocalDate startTime,
      @RequestParam @DateTimeFormat(iso = ISO.DATE) final LocalDate endTime, @RequestParam final int vehicleId,
      @RequestParam(required = false, defaultValue = "1") final int page,
      @RequestParam(required = false, defaultValue = "1000") final int pageSize) {
    return Mono.just(service.findAll(startTime, endTime, vehicleId, page, pageSize));
  }

}
