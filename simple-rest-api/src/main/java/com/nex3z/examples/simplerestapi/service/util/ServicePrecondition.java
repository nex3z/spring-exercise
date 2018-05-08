package com.nex3z.examples.simplerestapi.service.util;

import com.nex3z.examples.simplerestapi.service.exception.EntityNotFoundException;

public class ServicePrecondition {

    private ServicePrecondition() { throw new AssertionError(); }

    public static <T> T checkEntityExists(final T entity) {
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        return entity;
    }
}
