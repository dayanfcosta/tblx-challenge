package com.dayanfcosta.tblx.challenge.config;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import io.swagger.v3.oas.annotations.Parameter;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Parameter(example = "yyyy-MM-dd")
@Retention(RUNTIME)
@Target({PARAMETER})
public @interface DateParameter {

}
