package com.nex3z.examples.spring.backportautoconfigure.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.nex3z.examples.spring.greeter.backportautoconfigure")
public class BackportAutoConfigureDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackportAutoConfigureDemoApplication.class, args);
    }

}
