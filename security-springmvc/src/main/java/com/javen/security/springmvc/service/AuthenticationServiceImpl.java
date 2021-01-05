package com.javen.security.springmvc.service;

import com.javen.security.springmvc.model.AuthenticationRequest;
import com.javen.security.springmvc.model.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{


    @Override
    public UserDto authentication(AuthenticationRequest authenticationRequest) {
        if (authenticationRequest == null || StringUtils.isEmpty(authenticationRequest.getPassword()) || StringUtils.isEmpty(authenticationRequest.getUsername())) {
            throw new RuntimeException("账号或密码不能为空");
        }

        //模拟查询数据库
        UserDto user = getUserDto(authenticationRequest.getUsername());
        if (null == user) {
            throw new RuntimeException("用户不存在");
        }
        if (!authenticationRequest.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("密码错误");
        }

        return user;
    }

    private Map<String, UserDto> userDtoMap = new HashMap<>();
    {
        Set<String> auth1 = new HashSet<>();
        auth1.add("p1");
        Set<String> auth2 = new HashSet<>();
        auth2.add("p2");
        userDtoMap.put("jinwei", new UserDto("100", "jinwei", "123", "jinweif", "123444", auth1));
        userDtoMap.put("liuhe", new UserDto("101", "liuhe", "123", "liuhef", "123444", auth2));
    }

    private UserDto getUserDto(String name) {
        return userDtoMap.get(name);
    }
}
