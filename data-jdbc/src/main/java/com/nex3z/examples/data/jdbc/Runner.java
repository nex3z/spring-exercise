package com.nex3z.examples.data.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BatchUserDao batchUserDao;

    @Override
    public void run(String... args) throws Exception {
        userDao.insertData();
        batchUserDao.batchInsert();
        userDao.listData();
    }
}
