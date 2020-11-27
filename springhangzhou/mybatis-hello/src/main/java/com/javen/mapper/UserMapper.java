package com.javen.mapper;

import com.javen.pojo.User;

public interface UserMapper {

    User selectUser(Integer id);

    Integer add(User user);

    Integer delete(User user);

    Integer update(User user);
}
