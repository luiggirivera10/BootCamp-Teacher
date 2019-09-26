package com.everis.teacher.app;

import com.everis.teacher.app.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

/**
 * .
 * @author lriveras
 *
 */
@SpringBootApplication
@Import(SwaggerConfiguration.class)
@EnableDiscoveryClient
public class SpringTeacherApplication {

  /**
   * .
   */
  public static void main(final String[] args) {
    SpringApplication.run(SpringTeacherApplication.class, args);
  }  
}
