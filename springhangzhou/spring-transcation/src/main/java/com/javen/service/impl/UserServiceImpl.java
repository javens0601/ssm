package com.javen.service.impl;

import com.javen.dao.IUserDao;
import com.javen.entity.User;
import com.javen.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    public String getUserInfo(User user) {
        return String.valueOf(userDao.query(user));
    }
}
