package com.javen.service.impl;

import com.javen.dao.UserDao;
import com.javen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public void printName() {
        System.out.println(userDao.getUserName());
    }
}
