package com.nex3z.examples.simplerestapi.service.impl;

import com.nex3z.examples.simplerestapi.persistence.model.User;
import com.nex3z.examples.simplerestapi.persistence.repository.UserRepository;
import com.nex3z.examples.simplerestapi.service.IUserService;
import com.nex3z.examples.simplerestapi.service.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractService<User> implements IUserService {

    private final UserRepository userRepository;

    @Autowired
    UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    protected JpaRepository<User, Long> getDao() {
        return userRepository;
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name).orElse(null);
    }
}
