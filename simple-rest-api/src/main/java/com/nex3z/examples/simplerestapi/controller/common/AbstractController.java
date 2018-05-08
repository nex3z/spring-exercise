package com.nex3z.examples.simplerestapi.controller.common;

import com.nex3z.examples.simplerestapi.controller.util.RestPrecondition;
import com.nex3z.examples.simplerestapi.persistence.common.IEntity;

public abstract class AbstractController<T extends IEntity> extends AbstractReadOnlyController<T> {

    public AbstractController(final Class<T> clazz) {
        super(clazz);
    }

    protected final void createInternal(final T resource) {
        RestPrecondition.checkRequestElementNotNull(resource);
        RestPrecondition.checkRequestState(resource.getId() == null);
        getService().create(resource);
    }

}
