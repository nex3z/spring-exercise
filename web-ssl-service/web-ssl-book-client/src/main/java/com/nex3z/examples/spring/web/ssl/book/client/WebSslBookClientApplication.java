package com.nex3z.examples.spring.web.ssl.book.client;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class WebSslBookClientApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(WebSslBookClientApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.NONE)
                .run(args);
    }

}
