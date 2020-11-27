package com.javen.javaconfig;

import com.javen.bean.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
//@Component
@ComponentScan(basePackages = "com.javen")
@PropertySource(value = {"classpath:user.properties", "classpath:user2.properties"})

public class IocJavaConfig2 {

    @Value("${myInfo.age}")
    private int age;
    @Value("${myInfo.name}")
    private String name;
    @Value("${myInfo.address}")
    private String address;
    @Value("${myInfo.wife}")
    private String wife;

    @Bean(name = "userBean")
    @Scope(value = "prototype")
    public User user() {
        User user = new User();
        user.setAge(age);
        user.setName(name);
        user.setWife(wife);
        user.setAddress(address);
        return user;
    }
}
