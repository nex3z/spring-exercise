package com.nex3z.examples.spring.data.redis.repository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories
@EnableRedisRepositories
public class DataRedisRepositoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataRedisRepositoryApplication.class, args);
    }

}
