package de.lyth.pricingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Creates a Spring Boot Application which run the Pricing Service for us.
 * This Boot Application is registered as microservice for eureka servers.
 * TODO: Change this application to a microservice for REST API.
 */
@EnableEurekaClient
@SpringBootApplication
public class PricingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PricingServiceApplication.class, args);
    }

}
