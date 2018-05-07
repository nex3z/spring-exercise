package com.nex3z.examples.simplerestapi.controller.common;

import com.google.common.base.Preconditions;
import com.nex3z.examples.simplerestapi.controller.exception.ResourceNotFoundException;
import com.nex3z.examples.simplerestapi.controller.util.RestPrecondition;
import com.nex3z.examples.simplerestapi.persistence.common.IEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public abstract class AbstractReadOnlyController<T extends IEntity> {

    protected final Class<T> clazz;

    protected abstract JpaRepository<T, Long> getRepository();

    public AbstractReadOnlyController(final Class<T> clazz) {
        super();
        Preconditions.checkNotNull(clazz);
        this.clazz = clazz;
    }

    protected final T findOneInternal(final Long id) {
        return RestPrecondition.checkNotNull(getRepository().getOne(id),
                clazz.getSimpleName() + "with id" + id + "not found");
    }

    protected final List<T> findAllInternal(final HttpServletRequest request) {
        if (request.getParameterNames().hasMoreElements()) {
            throw new ResourceNotFoundException();
        }
        return getRepository().findAll();
    }

}
