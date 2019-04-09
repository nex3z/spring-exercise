package com.nex3z.examples.spring.hateoas.consumer.simple;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class HateoasConsumerSimpleApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(HateoasConsumerSimpleApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.NONE)
                .run(args);
    }

}
