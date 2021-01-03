package com.javen.controller;

import com.javen.api.HelloWorldApi;
import org.apache.servicecomb.provider.pojo.RpcReference;
import org.apache.servicecomb.provider.pojo.RpcSchema;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RpcSchema(schemaId = "rpcConsumerController")
@RequestMapping("/rpc")
public class RpcConsumerController {

    //通过Rpc协议远程调用rest协议的接口，
    // microserviceName是微服务提供者的名称，在服务提供者的microservice.yaml文件中配置的service_description:name
    // schemaId是微服务提供者的schemaId，在服务提供者的controller定义的时候有
    @RpcReference(microserviceName = "demo-provider", schemaId = "providerController")
    HelloWorldApi helloWorldInterfaces;

    @GetMapping("/request")
    public String sayHello(String name) {
        String hello = helloWorldInterfaces.sayHello(name);
        return hello;
    }

}