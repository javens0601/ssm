package com.javen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InterceptorController {

    @RequestMapping("/interceptor01")
    public String test01() {
        System.out.println("拦截器测试");
        return "main.jsp";
    }
}
