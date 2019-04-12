package com.nex3z.examples.spring.greeter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

@Slf4j
public class GreetingApplicationRunner implements ApplicationRunner {

    public GreetingApplicationRunner() {
        log.info("GreetingApplicationRunner(): initializing");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("run(): hello");
    }
}
