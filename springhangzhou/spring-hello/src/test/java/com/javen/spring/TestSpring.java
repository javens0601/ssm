package com.javen.spring;

import com.hangzhou.javen.Person;
import com.hangzhou.javen.Student;
import com.hangzhou.javen.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    private ApplicationContext context;

    @Before
    public void springBefore() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void testIoc() {
        Student student = context.getBean(Student.class);
        System.out.println(student);
    }

    @Test
    public void testIocAlias() {
        User user2 = context.getBean("user2", User.class);
        System.out.println(user2);
    }

    @Test
    public void testIocConstrcutor() {
        User user3 = context.getBean("user3", User.class);
        System.out.println(user3);
    }

    @Test
    public void testSpringRef() {
        Person person = context.getBean(Person.class);
        System.out.println(person);

    }
}
