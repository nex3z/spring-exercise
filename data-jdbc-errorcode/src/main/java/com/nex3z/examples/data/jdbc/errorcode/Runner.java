package com.nex3z.examples.data.jdbc.errorcode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component @Slf4j
public class Runner implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        try {
            jdbcTemplate.execute("INSERT INTO USER (ID, NAME ) VALUES (1, 'admin01')");
            jdbcTemplate.execute("INSERT INTO USER (ID, NAME ) VALUES (1, 'admin02')");
        } catch (CustomDuplicatedKeyException e) {
            log.error("run(): ", e);
        }
    }
}
