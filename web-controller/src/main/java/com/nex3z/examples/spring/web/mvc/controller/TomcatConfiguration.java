package com.nex3z.examples.spring.web.mvc.controller;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.Compression;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

@Configuration
public class TomcatConfiguration implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        // Same as setting server.compression.enabled=true and server.compression.min-response-size=512B
        // curl -H "Accept-Encoding: gzip" -v http://localhost:8080/coffee/1 --output -
        Compression compression = new Compression();
        compression.setEnabled(true);
        compression.setMinResponseSize(DataSize.ofBytes(512));
        factory.setCompression(compression);
    }

}
