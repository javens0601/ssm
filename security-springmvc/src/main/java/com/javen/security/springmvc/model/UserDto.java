package com.javen.security.springmvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserDto {
    //session key
    public static final String SESSION_KEY = "_user";
    private String id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;

    //用户权限
    private Set<String> authorities;
}
