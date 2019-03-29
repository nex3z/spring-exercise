package com.nex3z.examples.spring.mvc.interceptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EnableCaching
@SpringBootApplication
public class MvcInterceptorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvcInterceptorApplication.class, args);
    }

}
