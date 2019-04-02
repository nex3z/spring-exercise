package com.nex3z.examples.spring.web.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
public class WebSessionApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSessionApplication.class, args);
    }

}
