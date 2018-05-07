package com.nex3z.examples.simplerestapi.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ConflictException extends RuntimeException {

    public ConflictException() {
        super();
    }

    public ConflictException(String message) {
        super(message);
    }

}
