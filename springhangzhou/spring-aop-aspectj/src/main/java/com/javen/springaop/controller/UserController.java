package com.javen.springaop.controller;

import com.javen.springaop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public String getName(String name) {
        return userService.query(name);
    }
}
