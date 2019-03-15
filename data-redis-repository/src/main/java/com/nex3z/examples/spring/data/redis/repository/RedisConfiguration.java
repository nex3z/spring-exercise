package com.nex3z.examples.spring.data.redis.repository;

import com.nex3z.examples.spring.data.redis.repository.converter.BytesToMoneyConverter;
import com.nex3z.examples.spring.data.redis.repository.converter.MoneyToBytesConverter;
import io.lettuce.core.ReadFrom;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.convert.RedisCustomConversions;

import java.util.Arrays;

@Configuration
public class RedisConfiguration {

    @Bean
    public LettuceClientConfigurationBuilderCustomizer customizer() {
        return builder -> builder.readFrom(ReadFrom.MASTER_PREFERRED);
    }

    @Bean
    public RedisCustomConversions redisCustomConversions() {
        return new RedisCustomConversions(Arrays.asList(new MoneyToBytesConverter(), new BytesToMoneyConverter()));
    }

}
