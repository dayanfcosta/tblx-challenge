package com.dayanfcosta.tblx.challenge.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ResourceConfig extends ResponseEntityExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(ResourceConfig.class);

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public HttpEntity<HttpErrorResponse> handleInternalErrors(final IllegalArgumentException ex) {
    LOGGER.error("An error occurred: ", ex);
    final var errorResponse = HttpErrorResponse.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    return new ResponseEntity<>(errorResponse, httpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NullPointerException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public HttpEntity<HttpErrorResponse> handleInternalErrors(final NullPointerException ex) {
    LOGGER.error("An error occurred: ", ex);
    final var errorResponse = HttpErrorResponse.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    return new ResponseEntity<>(errorResponse, httpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public HttpEntity<HttpErrorResponse> handleInternalErrors(final Exception ex) {
    LOGGER.error("An error occurred: ", ex);
    final var errorResponse = HttpErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    return new ResponseEntity<>(errorResponse, httpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private HttpHeaders httpHeaders() {
    final var httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    return httpHeaders;
  }
}
