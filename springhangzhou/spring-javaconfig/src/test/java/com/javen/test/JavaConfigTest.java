package com.javen.test;

import com.javen.bean.Student;
import com.javen.bean.User;
import com.javen.javaconfig.IocJavaConfig;
import com.javen.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigTest {
    private ApplicationContext applicationContext;

    @Before
    public void initJavaConfig() {
        applicationContext = new AnnotationConfigApplicationContext(IocJavaConfig.class);
    }

    @Test
    public void test01() {
        UserService bean = applicationContext.getBean(UserService.class);
        System.out.println(bean.getUserName());
    }

    @Test
    public void test02() {
        User user = applicationContext.getBean("userBean", User.class);
        System.out.println(user);
    }

    @Test
    public void test03() {
        Student student = applicationContext.getBean(Student.class);
        System.out.println(student);
    }
}
