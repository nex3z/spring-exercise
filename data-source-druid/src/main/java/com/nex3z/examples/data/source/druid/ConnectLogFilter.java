package com.nex3z.examples.data.source.druid;

import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.ConnectionProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ConnectLogFilter extends FilterEventAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectLogFilter.class);

    @Override
    public void connection_connectBefore(FilterChain chain, Properties info) {
        LOGGER.info("connection_connectBefore()");
    }

    @Override
    public void connection_connectAfter(ConnectionProxy connection) {
        LOGGER.info("connection_connectAfter()");
    }

}
