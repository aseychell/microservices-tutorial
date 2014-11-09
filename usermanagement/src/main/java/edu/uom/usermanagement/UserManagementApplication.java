package edu.uom.usermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableWebMvc
public class UserManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserManagementApplication.class, args);
    }
}
