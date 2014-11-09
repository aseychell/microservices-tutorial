package edu.uom.healthservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class HealthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthServiceApplication.class, args);
    }
}
