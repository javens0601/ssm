package com.javen.security.springmvc.service;

import com.javen.security.springmvc.model.AuthenticationRequest;
import com.javen.security.springmvc.model.UserDto;

public interface AuthenticationService {
    UserDto authentication(AuthenticationRequest authenticationRequest);
}
