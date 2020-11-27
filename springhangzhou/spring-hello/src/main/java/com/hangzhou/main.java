package com.hangzhou;

import com.hangzhou.javen.Student;
import com.hangzhou.javen.Teacher;
import com.hangzhou.javen.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = applicationContext.getBean(Student.class);

        /*Student student = applicationContext.getBean(Student.class);
        User teacher = applicationContext.getBean(Teacher.class);*/
        System.out.println(user.toString());
        /*System.out.println(student.toString());
        System.out.println(teacher.toString());*/
    }
}
