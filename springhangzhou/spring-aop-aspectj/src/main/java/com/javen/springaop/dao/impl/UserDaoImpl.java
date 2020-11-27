package com.javen.springaop.dao.impl;

import com.javen.springaop.dao.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class UserDaoImpl implements UserDao<String> {

    @Deprecated
    public String query(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new NullPointerException();
        }
        System.out.println("query user by name : " + name);
        return "user info";
    }

    public void insert() {
        System.out.println("add user");
    }

    public void delete() {
        System.out.println("delete user");
    }

    public void update() {
        System.out.println("update user");
    }
}
