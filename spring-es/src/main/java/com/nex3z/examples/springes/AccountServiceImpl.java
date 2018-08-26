package com.nex3z.examples.springes;

import com.alibaba.fastjson.JSON;
import io.grpc.stub.StreamObserver;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl extends AccountServiceGrpc.AccountServiceImplBase {
    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final TransportClient client;

    public AccountServiceImpl() throws UnknownHostException {
        this.client = new PreBuiltTransportClient(Settings.builder().build())
                .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
    }

    @Override
    public void getAccountById(GetAccountByIdRequest request, StreamObserver<AccountResponse> responseObserver) {
        logger.info("getAccountById(): request = " + request);
        long id = request.getId();

        QueryBuilder queryBuilder = QueryBuilders
                .termQuery("account_number", id);

        SearchResponse searchResponse = client.prepareSearch("bank")
                .setTypes("_doc")
                .setQuery(queryBuilder)
                .get();

        List<SearchHit> hits = Arrays.asList(searchResponse.getHits().getHits());
        List<Account> accounts = hits.stream()
                .map(hit -> JSON.parseObject(hit.getSourceAsString(), AccountRecord.class))
                .map(AccountRecordMapper::transform)
                .collect(Collectors.toList());

        logger.info("getAccountById(): accounts = " + accounts);

        AccountResponse response = AccountResponse.newBuilder().addAllAccounts(accounts).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
