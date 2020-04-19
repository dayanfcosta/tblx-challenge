package com.dayanfcosta.tblx.challenge.config;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;

@Retention(RUNTIME)
@Target({METHOD, ANNOTATION_TYPE})
@ApiResponse(responseCode = "200", description = "Successful Operation")
public @interface SuccessfulApiResponse {

  @AliasFor(annotation = ApiResponse.class, attribute = "content")
  Content[] content() default {};

}
