package com.nex3z.examples.data.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SimpleJdbcInsert simpleJdbcInsert;

    public void insertData() {
        List<String> userNames = Arrays.asList("user-i1", "user-i2");
        userNames.forEach(user -> jdbcTemplate.update("INSERT INTO USER (NAME) VALUES (?)", user));

        Map<String, String> row = new HashMap<>();
        row.put("NAME", "user-i3");
        Number id = simpleJdbcInsert.executeAndReturnKey(row);
        log.info("insert(): id = {}", id);
    }

    public void listData() {
        Long count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USER", Long.class);
        log.info("listData(): count = {}", count);

        List<String> list = jdbcTemplate.queryForList("SELECT NAME FROM USER", String.class);
        log.info("listData(): list = {}", list);

        List<User> userList = jdbcTemplate.query("SELECT * FROM USER",
                (resultSet, i) -> User.builder()
                        .id(resultSet.getLong(1))
                        .name(resultSet.getString(2))
                        .build());
        log.info("listData(): userList = {}", userList);
    }
}
