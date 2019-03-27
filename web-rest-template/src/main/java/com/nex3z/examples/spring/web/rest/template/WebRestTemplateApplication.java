package com.nex3z.examples.spring.web.rest.template;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class WebRestTemplateApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(WebRestTemplateApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.NONE)
                .run(args);
    }

}
