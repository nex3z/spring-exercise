package com.nex3z.examples.springes;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class AccountServiceConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceConfiguration.class);

    @Autowired
    private AccountServiceImpl accountService;

    @Bean(destroyMethod = "shutdown")
    public Server server() throws IOException {
        LOGGER.info("server(): Starting Account Service");
        Server server = ServerBuilder.forPort(50051)
                .addService(accountService)
                .build()
                .start();
        return server;
    }

}
