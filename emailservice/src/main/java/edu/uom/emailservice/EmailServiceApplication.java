package edu.uom.emailservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class EmailServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailServiceApplication.class, args);
    }
}
