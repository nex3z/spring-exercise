package com.nex3z.examples.data.source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class DataSourceConfiguration implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfiguration.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        showConnection();
        showData();
    }

    private void showConnection() throws SQLException {
        LOGGER.info("showConnection(): dataSource = {}", dataSource);
        Connection connection = dataSource.getConnection();
        LOGGER.info("showConnection(): connection = {}", connection);
        connection.close();
    }

    private void showData() {
        jdbcTemplate.queryForList("SELECT * FROM MOVIE")
                .forEach(row -> LOGGER.info("showData(): {}", row));
    }
}
