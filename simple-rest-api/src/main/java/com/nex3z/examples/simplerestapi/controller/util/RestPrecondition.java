package com.nex3z.examples.simplerestapi.controller.util;

import com.nex3z.examples.simplerestapi.controller.exception.BadRequestException;
import com.nex3z.examples.simplerestapi.controller.exception.ConflictException;
import com.nex3z.examples.simplerestapi.controller.exception.ResourceNotFoundException;

import java.util.Optional;

public class RestPrecondition {

    private RestPrecondition() {
        throw new AssertionError();
    }

    public static <T> T checkNotNull(final T ref) {
        return checkNotNull(ref, null);
    }

    public static <T> T checkNotNull(final T ref, String message) {
        if (ref == null) {
            throw new ResourceNotFoundException(message);
        }
        return ref;
    }

    public static <T extends Optional<R>, R> R checkIsPresent(final T opt, String message) {
        return opt.orElseThrow(() -> new ResourceNotFoundException(message));
    }

    public static <T> T checkRequestElementNotNull(T ref) {
        return checkRequestElementNotNull(ref, null);
    }

    public static <T> T checkRequestElementNotNull(T ref, String message) {
        if (ref == null) {
            throw new BadRequestException(message);
        }
        return ref;
    }

    public static void checkRequestState(final boolean expression) {
        checkRequestState(expression, null);
    }

    public static void checkRequestState(final boolean expression, final String message) {
        if (!expression) {
            throw new ConflictException(message);
        }
    }
}
