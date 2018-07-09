package com.nex3z.examples.simplegrpc;

import com.nex3z.examples.simplegrpcserver.Note;
import com.nex3z.examples.simplegrpcserver.NoteServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NoteClient {
    private static final Logger logger = Logger.getLogger(NoteClient.class.getName());

    private final ManagedChannel channel;
    private final NoteServiceGrpc.NoteServiceBlockingStub blockingStub;

    public NoteClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build());
    }

    private NoteClient(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = NoteServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void addNote(String content) {
        logger.info("Adding note " + content + " ...");
        Note request = Note.newBuilder().setContent(content).build();
        Note response;
        try {
            response = blockingStub.addNote(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
        logger.info("Result: " + response.getContent());
    }

    public static void main(String[] args) throws Exception {
        NoteClient client = new NoteClient("localhost", 6565);
        try {
            String content = "hello world";
            client.addNote(content);
        } finally {
            client.shutdown();
        }
    }
}
