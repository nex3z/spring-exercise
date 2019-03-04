package com.nex3z.examples.spring.data.transaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component @Slf4j
public class UserServiceRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        userService.insertRecord();
        log.info("run(): insertRecord A {}",
                jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USER WHERE NAME='A'", Long.class));

        try {
            userService.insertThenRollback();
        } catch (Exception e) {
            log.info("run(): insertThenRollback B {}",
                    jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USER WHERE NAME='B'", Long.class));
        }

        try {
            userService.invokeInsertThenRollback();
        } catch (Exception e) {
            log.info("run(): invokeInsertThenRollback B {}",
                    jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USER WHERE NAME='B'", Long.class));
        }
    }
}
