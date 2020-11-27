package com.javen.dao;

import com.javen.entity.User;

public interface IUserDao {
    User query(User user);
    int add(User user);
    void delete(User user);
    void update(User user);
}
