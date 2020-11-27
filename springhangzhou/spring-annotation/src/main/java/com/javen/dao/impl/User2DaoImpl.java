package com.javen.dao.impl;

import com.javen.dao.UserDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class User2DaoImpl implements UserDao {
    public String getUserName() {
        return this.getClass().getName();
    }
}
