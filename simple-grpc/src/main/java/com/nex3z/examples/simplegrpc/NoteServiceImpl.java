package com.nex3z.examples.simplegrpc;

import com.nex3z.examples.simplegrpcserver.Note;
import com.nex3z.examples.simplegrpcserver.NoteServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GRpcService
public class NoteServiceImpl extends NoteServiceGrpc.NoteServiceImplBase {

    private Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);

    @Override
    public void addNote(Note request, StreamObserver<Note> responseObserver) {
        logger.debug("addNote(): request = " + request);
        Note note =  Note.newBuilder(request).build();
        responseObserver.onNext(note);
        responseObserver.onCompleted();
    }

}
