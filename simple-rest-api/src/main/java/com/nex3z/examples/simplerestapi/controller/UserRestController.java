package com.nex3z.examples.simplerestapi.controller;

import com.nex3z.examples.simplerestapi.controller.common.AbstractController;
import com.nex3z.examples.simplerestapi.persistence.model.User;
import com.nex3z.examples.simplerestapi.persistence.repository.UserRepository;
import com.nex3z.examples.simplerestapi.service.IUserService;
import com.nex3z.examples.simplerestapi.service.common.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@RestController
@RequestMapping("users")
public class UserRestController extends AbstractController<User> {

    private final IUserService userService;

    @Autowired
    UserRestController(IUserService userService) {
        super(User.class);
        this.userService = userService;
    }

    @Override
    protected IService<User> getService() {
        return userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<User> getUsers(final HttpServletRequest request) {
        return findAllInternal(request);
    }

}
