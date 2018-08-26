package com.nex3z.examples.springes;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class AccountClient {
    private static final Logger logger = LoggerFactory.getLogger(AccountClient.class);

    private final ManagedChannel channel;
    private final AccountServiceGrpc.AccountServiceBlockingStub blockingStub;

    /** Construct client connecting to HelloWorld server at {@code host:port}. */
    public AccountClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                // needing certificates.
                .usePlaintext()
                .build());
    }

    /** Construct client for accessing RouteGuide server using the existing channel. */
    AccountClient(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = AccountServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /** Say hello to server. */
    public void greet(String name) {
        logger.info("Will try to greet " + name + " ...");
        GetAccountByIdRequest request = GetAccountByIdRequest.newBuilder().setId(1).build();
        AccountResponse response;
        try {
            response = blockingStub.getAccountById(request);
        } catch (StatusRuntimeException e) {
            e.printStackTrace();
            return;
        }
        logger.info("Greeting: " + response.getAccountsList());
    }

    /**
     * Greet server. If provided, the first element of {@code args} is the name to use in the
     * greeting.
     */
    public static void main(String[] args) throws Exception {
        AccountClient client = new AccountClient("localhost", 50051);
        try {
            /* Access a service running on the local machine on port 50051 */
            String user = "world";
            if (args.length > 0) {
                user = args[0]; /* Use the arg as the name to greet if provided */
            }
            client.greet(user);
        } finally {
            client.shutdown();
        }
    }
}
