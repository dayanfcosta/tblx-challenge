package com.dayanfcosta.tblx.challenge.trace;

import com.dayanfcosta.tblx.challenge.config.DateParameter;
import com.dayanfcosta.tblx.challenge.config.QueryApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping
  @QueryApiResponse(
      summary = "Find all traces of a given vehicle with the given range of time frame",
      content = @Content(array = @ArraySchema(schema = @Schema(implementation = Trace.class)))
  )
  Mono<List<Trace>> findAll(
      @DateParameter @RequestParam @DateTimeFormat(iso = ISO.DATE) final LocalDate startTime,
      @DateParameter @RequestParam @DateTimeFormat(iso = ISO.DATE) final LocalDate endTime, @RequestParam final int vehicleId,
      @RequestParam(required = false, defaultValue = "0") final int page,
      @RequestParam(required = false, defaultValue = "1000") final int pageSize) {
    return Mono.just(service.findAll(startTime, endTime, vehicleId, page, pageSize));
  }

}
