package com.dayanfcosta.tblx.challenge.operator;

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
@RequestMapping("/operators")
class OperatorResource {

  private final OperatorService service;

  OperatorResource(final OperatorService service) {
    this.service = service;
  }

  @GetMapping
  @QueryApiResponse(
      summary = "Find all operators given a range of time frame",
      content = @Content(array = @ArraySchema(schema = @Schema(implementation = String.class)))
  )
  public Mono<Set<String>> find(
      @DateParameter @RequestParam @DateTimeFormat(iso = ISO.DATE) final LocalDate startTime,
      @DateParameter @RequestParam @DateTimeFormat(iso = ISO.DATE) final LocalDate endTime) {
    return Mono.just(service.findAll(startTime, endTime));
  }

}