package com.nex3z.examples.simplerestapi.service.common;

import com.google.common.base.Preconditions;
import com.nex3z.examples.simplerestapi.persistence.common.IEntity;
import com.nex3z.examples.simplerestapi.service.util.ServicePrecondition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class AbstractService<T extends IEntity> implements IService<T> {

    protected abstract JpaRepository<T, Long> getDao();

    @Override
    public T findOne(long id) {
        return getDao().findById(id).orElse(null);
    }

    @Override
    public List<T> findAll() {
        return getDao().findAll();
    }

    @Override
    public T create(T resource) {
        Preconditions.checkNotNull(resource);
        return getDao().save(resource);
    }

    @Override
    public void update(T resource) {
        Preconditions.checkNotNull(resource);
        getDao().save(resource);
    }

    @Override
    public void delete(long id) {
        final T entity = getDao().findById(id).orElse(null);
        ServicePrecondition.checkEntityExists(entity);
        getDao().delete(entity);
    }
}
