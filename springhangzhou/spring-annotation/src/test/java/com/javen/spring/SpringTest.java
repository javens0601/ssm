package com.javen.spring;

import com.javen.controller.UserController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

    private ApplicationContext context;

    @Before
    public void TestInitBefore() {
        context = new ClassPathXmlApplicationContext("spring-ioc.xml");
    }
    @Test
    public void TestBean() {
        UserController userController = context.getBean(UserController.class);
        System.out.println(userController);
    }
}
