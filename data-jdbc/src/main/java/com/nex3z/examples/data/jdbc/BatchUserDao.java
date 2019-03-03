package com.nex3z.examples.data.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BatchUserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void batchInsert() {
        jdbcTemplate.batchUpdate("INSERT INTO USER(NAME) VALUES (?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setString(1, "user-b1" + i);
            }

            @Override
            public int getBatchSize() {
                return 2;
            }
        });

        List<User> list = new ArrayList<>();
        list.add(User.builder().id(20L).name("user-b20").build());
        list.add(User.builder().id(21L).name("user-b21").build());
        namedParameterJdbcTemplate.batchUpdate("INSERT INTO USER (ID, NAME) VALUES (:id, :name)",
                SqlParameterSourceUtils.createBatch(list));
    }
}
