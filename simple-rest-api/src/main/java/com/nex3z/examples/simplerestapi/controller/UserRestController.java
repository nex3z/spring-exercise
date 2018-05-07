package com.nex3z.examples.simplerestapi.controller;

import com.nex3z.examples.simplerestapi.controller.common.AbstractController;
import com.nex3z.examples.simplerestapi.persistence.model.User;
import com.nex3z.examples.simplerestapi.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@RestController
@RequestMapping("users")
public class UserRestController extends AbstractController<User> {

    private final UserRepository userRepository;

    @Autowired
    UserRestController(UserRepository userRepository) {
        super(User.class);
        this.userRepository = userRepository;
    }

    @Override
    protected JpaRepository<User, Long> getRepository() {
        return userRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<User> getUsers(final HttpServletRequest request) {
        return findAllInternal(request);
    }

}
