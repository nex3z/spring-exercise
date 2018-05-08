package com.nex3z.examples.simplerestapi.service.common;

import java.io.Serializable;
import java.util.List;

public interface IOperations<T extends Serializable> {

    T findOne(final long id);

    List<T> findAll();

    T create(final T resource);

    void update(final T resource);

    void delete(final long id);

}
