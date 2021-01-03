package com.javen.controller;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;


@RestSchema(schemaId = "consumerController")
@RequestMapping("/")
public class ConsumerController {
    //定义远程访问的RestTemplate
    private final RestTemplate restTemplate = RestTemplateBuilder.create();

    @GetMapping("request")
    public String sayHello(String name) {
        //service url is : cse://serviceName/operation
        // provider是 serviceprovider项目中的microservice.yaml 里面的name 微服务名称
        String serviceName = "demo-provider";
        String result = restTemplate.getForObject("cse://" + serviceName + "/hello?name=" + name, String.class);
        return result;
    }
}