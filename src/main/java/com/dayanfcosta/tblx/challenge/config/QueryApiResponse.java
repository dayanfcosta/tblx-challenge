package com.dayanfcosta.tblx.challenge.config;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;

@Retention(RUNTIME)
@Target({METHOD, ANNOTATION_TYPE})
@Operation
@SuccessfulApiResponse
@ApiResponses(value = {
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = HttpErrorResponse.class))),
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = HttpErrorResponse.class)))
})
public @interface QueryApiResponse {

  @AliasFor(annotation = Operation.class)
  String summary() default "";

  @AliasFor(annotation = SuccessfulApiResponse.class)
  Content[] content() default {};

}