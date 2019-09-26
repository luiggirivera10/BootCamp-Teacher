package com.everis.teacherconsumer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * .
 * @author lriveras
 *
 */
@SpringBootApplication
@EnableHystrixDashboard
@EnableCircuitBreaker
@EnableDiscoveryClient
public class SpringTeacherconsumerApplication {

  /**
 * main.
 */
	public static void main(String[] args) {
		SpringApplication.run(SpringTeacherconsumerApplication.class, args);
	}

	  /**
	 *.
	 */
	    @Bean
	    public RestTemplate restTemplate() {
	      return new RestTemplate();
	    }
}
