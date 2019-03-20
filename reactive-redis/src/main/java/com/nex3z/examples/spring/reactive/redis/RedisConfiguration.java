package com.nex3z.examples.spring.reactive.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;

@Configuration
public class RedisConfiguration {

    @Bean
    public ReactiveStringRedisTemplate reactiveStringRedisTemplate(ReactiveRedisConnectionFactory factory) {
        return new ReactiveStringRedisTemplate(factory);
    }

}
