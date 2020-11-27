package com.javen.springaop.service.impl;

import com.javen.springaop.dao.UserDao;
import com.javen.springaop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public String query(String name) {
        return userDao.query(name);
    }

    public void insert() {
        userDao.insert();
    }

    public void update() {
        userDao.update();
    }

    public void delete() {
        userDao.delete();
    }
}
