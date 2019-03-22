package com.nex3z.examples.spring.reactive.r2dbc.repository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
public class ReactiveR2dbcRepositoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveR2dbcRepositoryApplication.class, args);
    }

}
