package com.everis.teacher.app.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

/**
 * .
 * @author lriveras
 *
 */
@Configuration
@EnableSwagger2WebFlux
public class SwaggerConfiguration {
  /**
 * SwaggerStudentApi.
 */
  @Bean
  public Docket teacherApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.everis.teacher.app.controller"))
        .paths(regex("/api/v1.0/teachers.*"))
        .build();
  }

  /**
   * .
   */
  public SwaggerConfiguration() {
    super();
  }


}
