package com.nex3z.examples.data.source.multiple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfiguration.class);

    @Bean @ConfigurationProperties("first.datasource")
    public DataSourceProperties firstDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource firstDataSource() {
        DataSourceProperties properties = firstDataSourceProperties();
        LOGGER.info("firstDataSource(): url = {}", properties.getUrl());
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean
    public PlatformTransactionManager firstTransactionManager(DataSource firstDataSource) {
        return new DataSourceTransactionManager(firstDataSource);
    }

    @Bean @ConfigurationProperties("second.datasource")
    public DataSourceProperties secondDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource secondDataSource() {
        DataSourceProperties properties = secondDataSourceProperties();
        LOGGER.info("secondDataSource(): url = {}", properties.getUrl());
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean
    public PlatformTransactionManager secondTransactionManager(DataSource secondDataSource) {
        return new DataSourceTransactionManager(secondDataSource);
    }

}
