package com.github.virginonline.planetsandlords;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class PlanetsAndLordsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlanetsAndLordsApplication.class, args);
    }
}
