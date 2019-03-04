package com.nex3z.examples.spring.data.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override @Transactional
    public void insertRecord() {
        jdbcTemplate.execute("INSERT INTO USER (NAME) VALUES ('A')");
    }

    @Override @Transactional(rollbackFor = RollbackException.class)
    public void insertThenRollback() throws RollbackException {
        jdbcTemplate.execute("INSERT INTO USER (NAME) VALUES ('B')");
        throw new RollbackException();
    }

    @Override // @Transactional(rollbackFor = RollbackException.class)
    public void invokeInsertThenRollback() throws RollbackException {
        insertThenRollback();
    }
}
