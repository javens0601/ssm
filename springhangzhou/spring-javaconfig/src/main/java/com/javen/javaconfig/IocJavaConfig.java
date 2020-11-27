package com.javen.javaconfig;

import com.alibaba.druid.pool.DruidDataSource;
import com.javen.bean.MyImportBeanDefinitation;
import com.javen.bean.MyImportSeletor;
import com.javen.bean.Student;
import com.javen.bean.User;
import com.javen.service.UserService;
import com.javen.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlEnumValue;

@Configuration
//@Component
@ComponentScan(basePackages = "com.javen")
@PropertySource(value = {"classpath:user.properties", "classpath:user2.properties"})
/**
 * @Import  1、引入其他的配置类
 *          2、将其他的类注册为一个bean
 *          3、导入ImportSelector实现类，可以注册多个bean
 */
@Import(MyImportSeletor.class)
//@Import(MyImportBeanDefinitation.class)
public class IocJavaConfig {

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
    public User user(Student student) {
        User user = new User();
        user.setAge(age);
        user.setName(name);
        user.setWife(wife);
        user.setAddress(address);
        System.out.println(student);
        return user;
    }
}
