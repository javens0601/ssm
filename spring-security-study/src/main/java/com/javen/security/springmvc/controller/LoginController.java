package com.javen.security.springmvc.controller;

import com.javen.security.springmvc.model.AuthenticationRequest;
import com.javen.security.springmvc.model.UserDto;
import com.javen.security.springmvc.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/login", produces = "text/plaint;charset=utf-8")
    public String  login(AuthenticationRequest request, HttpSession session) {
        UserDto userDto = authenticationService.authentication(request);
        //存入session
        session.setAttribute(UserDto.SESSION_KEY, userDto);
        return userDto.getUsername() + " 登陆成功";
    }

    @GetMapping(value = "/r/r1", produces = {"text/plaint;charset=UTF-8"})
    public String r1(HttpSession session) {
        Object attribute = session.getAttribute(UserDto.SESSION_KEY);
        if (null == attribute) {
            return "未登录";
        }
        return ((UserDto)attribute).getUsername() + " 已经的登陆";
    }

    @GetMapping(value = "/r/r2", produces = {"text/plaint;charset=UTF-8"})
    public String r2(HttpSession session) {
        Object attribute = session.getAttribute(UserDto.SESSION_KEY);
        if (null == attribute) {
            return "未登录";
        }
        return ((UserDto)attribute).getUsername() + " 已经的登陆";
    }

    @GetMapping(value = "/logout", produces = {"text/plaint;charset=utf-8"})
    public String logout(HttpSession session) {
        Object attribute = session.getAttribute(UserDto.SESSION_KEY);
        if (null == attribute) {
            throw new RuntimeException("退出异常");
        }
        String username = ((UserDto)attribute).getUsername();
        session.invalidate();
        return username + " 退出登陆";
    }

}
