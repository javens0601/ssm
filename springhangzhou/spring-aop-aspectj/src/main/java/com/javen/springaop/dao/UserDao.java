package com.javen.springaop.dao;

public interface UserDao<T> {
    String query(T name);
    void insert();
    void delete();
    void update();
}
