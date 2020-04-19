package com.dayanfcosta.tblx.challenge.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI api(@Value("${springdoc.version}") final String appVersion) {
    return new OpenAPI().info(metaData());
  }

  private Info metaData() {
    return new Info()
        .title("tb.lx traces REST API")
        .version("1.0.0")
        .license(license());
  }

  private License license() {
    return new License()
        .name("Apache License Version 2.0")
        .url("https://www.apache.org/licenses/LICENSE-2.0");
  }

}
