package com.nex3z.examples.simplerestapi.service.common;

import com.nex3z.examples.simplerestapi.persistence.common.IHasName;

public interface IFindByName<T extends IHasName> {

    T findByName(final String name);

}
