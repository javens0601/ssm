package com.javen.security.springmvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping(value = "/login-success", produces = {"text/plaint;charset=utf-8"})
    public String loginSuccess() {
        return "login success";
    }

}
