package com.javen.spring;

import com.javen.bean.User;
import com.javen.controller.UserController;
import com.javen.dao.impl.UserDaoImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTestAnnotation {
    private ApplicationContext context;

    @Before
    public void initContext() {
        context = new ClassPathXmlApplicationContext("spring-ioc-annotation.xml");
    }

    @Test
    public void springAnnotation() {
        UserController userController = context.getBean(UserController.class);
//        UserDaoImpl userDao = context.getBean(UserDaoImpl.class);
        System.out.println(userController);
//        System.out.println(userDao);
    }
    @Test
    public void springTest01() {
        User bean = context.getBean(User.class);
        System.out.println(bean);
    }

    @Test
    public void springTest02() {
        UserController userController = context.getBean(UserController.class);
        userController.print();
    }
}
