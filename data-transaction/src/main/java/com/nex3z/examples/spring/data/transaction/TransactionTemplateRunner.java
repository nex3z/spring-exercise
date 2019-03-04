package com.nex3z.examples.spring.data.transaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Component @Slf4j
public class TransactionTemplateRunner implements ApplicationRunner {

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        printCount();
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                jdbcTemplate.execute("INSERT INTO USER (ID, NAME) VALUES (1, 'admin')");
                printCount();
                transactionStatus.setRollbackOnly();
            }
        });
    }

    private void printCount() {
        long count = (long) jdbcTemplate.queryForList("SELECT COUNT(*) AS CNT FROM USER").get(0).get("CNT");
        log.info("printCount(): count = {}", count);
    }
}
