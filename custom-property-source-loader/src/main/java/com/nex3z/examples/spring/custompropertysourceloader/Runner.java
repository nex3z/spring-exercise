package com.nex3z.examples.spring.custompropertysourceloader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Runner implements ApplicationRunner {

    @Value("${name}")
    private String name;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("run(): hello, {}", name);
    }
}
