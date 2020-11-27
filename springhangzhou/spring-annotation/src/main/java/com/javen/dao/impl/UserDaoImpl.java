package com.javen.dao.impl;

import com.javen.dao.UserDao;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    public String getUserName() {
        return this.getClass().getName();
    }
}
