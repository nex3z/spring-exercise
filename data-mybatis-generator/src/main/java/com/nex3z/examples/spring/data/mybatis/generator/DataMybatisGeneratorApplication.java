package com.nex3z.examples.spring.data.mybatis.generator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.nex3z.examples.spring.data.mybatis.generator.mapper")
public class DataMybatisGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataMybatisGeneratorApplication.class, args);
    }

}
