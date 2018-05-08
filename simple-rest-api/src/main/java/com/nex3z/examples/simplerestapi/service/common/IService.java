package com.nex3z.examples.simplerestapi.service.common;

import com.nex3z.examples.simplerestapi.persistence.common.IEntity;

public interface IService<T extends IEntity> extends IOperations<T> {
}
