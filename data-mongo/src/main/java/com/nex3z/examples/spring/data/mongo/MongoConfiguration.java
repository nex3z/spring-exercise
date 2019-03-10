package com.nex3z.examples.spring.data.mongo;

import com.nex3z.examples.spring.data.mongo.converter.MoneyReadConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.Collections;

@Configuration
public class MongoConfiguration {

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Collections.singletonList(new MoneyReadConverter()));
    }

}
