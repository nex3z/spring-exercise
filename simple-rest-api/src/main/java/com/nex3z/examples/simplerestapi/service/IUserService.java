package com.nex3z.examples.simplerestapi.service;

import com.nex3z.examples.simplerestapi.persistence.model.User;
import com.nex3z.examples.simplerestapi.service.common.IFindByName;
import com.nex3z.examples.simplerestapi.service.common.IService;

public interface IUserService extends IService<User>, IFindByName<User> {
}
