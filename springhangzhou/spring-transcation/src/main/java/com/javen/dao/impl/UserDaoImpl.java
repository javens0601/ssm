package com.javen.dao.impl;

import com.javen.dao.IUserDao;
import com.javen.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements IUserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User query(User user) {
        User user1 = jdbcTemplate.queryForObject("select * from user where id=1", new BeanPropertyRowMapper<>(User.class));
        return user1;
    }

    public int add(User user) {
        return 0;
    }

    public void delete(User user) {

    }

    public void update(User user) {

    }
}
