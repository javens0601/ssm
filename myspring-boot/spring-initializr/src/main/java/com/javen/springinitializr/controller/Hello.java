package com.javen.springinitializr.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/spring")
public class Hello {
    @Value("${my.var}")
    private String address;

    @GetMapping(value = "/hello/{name}", produces = "application/json")
    public String sayHello(@PathVariable("name") String name1) {
        return "hello devtools " + name1 + " " + address;
    }

    public Hello() {
        System.out.println("构造函数加载");
    }
}
