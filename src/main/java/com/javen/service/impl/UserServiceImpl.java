package com.javen.service.impl;

import com.javen.mapper.UserMapper;
import com.javen.pojo.User;
import com.javen.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public String queryUser(Integer userId) {
        log.debug("用户信息查询接口, userId {}", userId);
        User user = userMapper.selectByPrimaryKey(userId);
        return user.toString();
    }
}
