package com.javen.controller;

import com.javen.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserSSOController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/login/{userId}", produces = {"application/json;charset=UTF-8"})
    public String login(@PathVariable("userId") Integer userId) {
        log.debug("通过userId {} 查询用户信息", userId);
        String userInfo = userService.queryUser(userId);
        System.out.println(userInfo);
        return userInfo;
    }
}
