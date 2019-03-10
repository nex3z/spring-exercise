package com.nex3z.examples.spring.data.mongo.repository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class DataMongoRepositoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataMongoRepositoryApplication.class, args);
    }

}
