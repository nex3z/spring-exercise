package com.nex3z.spring.examples.webflux.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
public class WebfluxSimpleApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxSimpleApplication.class, args);
    }

}
