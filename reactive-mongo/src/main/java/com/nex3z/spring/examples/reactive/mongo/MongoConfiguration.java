package com.nex3z.spring.examples.reactive.mongo;

import com.nex3z.spring.examples.reactive.mongo.converter.MoneyReadConverter;
import com.nex3z.spring.examples.reactive.mongo.converter.MoneyWriteConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.Arrays;

@Configuration
public class MongoConfiguration {

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Arrays.asList(
                new MoneyReadConverter(),
                new MoneyWriteConverter())
        );
    }

}
