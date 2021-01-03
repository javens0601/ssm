package com.javen.controller;


import com.javen.api.HelloWorldApi;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/")
@RestSchema(schemaId = "providerController")
public class ProviderController implements HelloWorldApi {
    @GetMapping("hello")

    @Override
    public String sayHello(String name) {
        System.out.println("名称是："+name);
        return "请求成功，返回名称："+name;
    }
}