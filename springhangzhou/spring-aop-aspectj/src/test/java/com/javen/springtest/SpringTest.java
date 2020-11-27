package com.javen.springtest;

import com.javen.springaop.controller.UserController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
    private ApplicationContext context;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("springContext.xml");
    }

    @Test
    public void test01() {
        UserController controller = context.getBean(UserController.class);
        System.out.println(controller.getClass());
        controller.getName("javen");
    }
}
