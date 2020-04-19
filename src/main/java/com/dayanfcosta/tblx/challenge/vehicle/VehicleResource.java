package com.dayanfcosta.tblx.challenge.vehicle;

import com.dayanfcosta.tblx.challenge.config.DateParameter;
import com.dayanfcosta.tblx.challenge.config.QueryApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
  @QueryApiResponse(
      summary = "Find all vehicle ids given a range of time frame, an operator and a flag indicating if it was at stop or wasn't",
      content = @Content(array = @ArraySchema(schema = @Schema(implementation = Integer.class)))
  )
  Mono<Set<Integer>> findAll(
      @DateParameter @RequestParam @DateTimeFormat(iso = ISO.DATE) final LocalDate startTime,
      @DateParameter @RequestParam @DateTimeFormat(iso = ISO.DATE) final LocalDate endTime,
      @RequestParam final String operator,
      @RequestParam(defaultValue = "false", required = false) final boolean atStop) {
    return Mono.just(service.findAll(startTime, endTime, operator, atStop));
  }
}
