package com.dayanfcosta.tblx.challenge.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.http.HttpStatus;

@JsonInclude(Include.NON_NULL)
public class HttpErrorResponse {

  private final int status;
  private final String error;
  private final String message;

  @JsonCreator
  private HttpErrorResponse(final int status, final String error, final String message) {
    this.status = status;
    this.error = error;
    this.message = message;
  }

  public int getStatus() {
    return status;
  }

  public String getError() {
    return error;
  }

  public String getMessage() {
    return message;
  }

  public static HttpErrorResponse of(final HttpStatus status, final String message) {
    return new HttpErrorResponse(status.value(), status.getReasonPhrase(), message);
  }

}
