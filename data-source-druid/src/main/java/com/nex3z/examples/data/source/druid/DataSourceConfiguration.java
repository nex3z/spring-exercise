package com.nex3z.examples.data.source.druid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfiguration.class);

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("run(): dataSource = {}", dataSource);
    }
}
