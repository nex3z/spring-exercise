package com.nex3z.examples.simplerestapi.persistence.controller.util;

import com.nex3z.examples.simplerestapi.persistence.controller.exception.ResourceNotFoundException;

import java.util.Optional;

public class Precondition {

    private Precondition() {
        throw new AssertionError();
    }

    public static <T> T checkNotNull(final T reference) {
        return checkNotNull(reference, "");
    }

    public static <T> T checkNotNull(final T reference, String message) {
        if (reference == null) {
            throw new ResourceNotFoundException(message);
        }
        return reference;
    }

    public static <T extends Optional<R>, R> R checkIsPresent(final T opt, String message) {
        return opt.orElseThrow(() -> new ResourceNotFoundException(message));
    }
}
