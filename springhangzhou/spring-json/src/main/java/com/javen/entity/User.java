package com.javen.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.scene.chart.PieChart;

import java.util.Date;
import java.util.Map;

public class User {

    private Integer age;

    //当返回的javaBean的json时，会忽略该属性
    @JsonIgnore
    private String name;

    private Map<String, String> hobby;

    @JsonFormat(pattern = "dd/mm/yyyy")
    private Date  date;

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", hobby=" + hobby +
                ", date=" + date +
                '}';
    }

    public void setDate(Date date) {
        this.date = date;
    }

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

    public Map<String, String> getHobby() {
        return hobby;
    }

    public void setHobby(Map<String, String> hobby) {
        this.hobby = hobby;
    }
}
