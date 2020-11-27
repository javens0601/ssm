package com.hangzhou.javen;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Person implements InitializingBean, DisposableBean {
    private Integer age;
    private String name;
    private String eender;
    private Date birthday;
    private List<String> hobbies;
    private Map<String, String> course;
    private Wife wife;


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEender() {
        return eender;
    }

    public void setEender(String eender) {
        this.eender = eender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public Map<String, String> getCourse() {
        return course;
    }

    public void setCourse(Map<String, String> course) {
        this.course = course;
    }

    public Wife getWife() {
        return wife;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", eender='" + eender + '\'' +
                ", birthday=" + birthday +
                ", hobbies=" + hobbies +
                ", course=" + course +
                ", wife=" + wife +
                '}';
    }

    public void destroy() throws Exception {
        System.out.println("销毁bean 1");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("实例化bean 1");
    }

    public void destroyByConfig() throws Exception {
        System.out.println("销毁bean 2");
    }

    public void initByConfig() throws Exception {
        System.out.println("实例化bean 2");
    }
}
