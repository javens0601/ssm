package com.javen.controller;

import com.javen.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorld {

    /**
     * RequestMapping 方法映射
     * @param name
     * @return
     */
    @RequestMapping(value = "hello")
    public String hello(@RequestParam(value = "user", required = false, defaultValue = "123") String name) {
        System.out.println("hello word ss" + name);

        //redirect 重定向
        //forward 转发 默认
        return "redirect:param.jsp";
    }

    /**
     * 复杂数据类型的映射
     * @param user
     * @return
     */
    @RequestMapping(value = "hello2")
    public String hello02(User user) {
        System.out.println(user.toString());

        return "redirect:param.jsp";
    }

    /**
     * 获取请求头信息
     * @param host
     * @return
     */
    @RequestMapping(value = "hello3")
    public String hello03(@RequestHeader("Host") String host) {
        System.out.println(host);

        return "redirect:param.jsp";
    }

    @RequestMapping(value = "hello4")
    public String hello04(@CookieValue("JSESSIONID") String sessionid) {
        System.out.println(sessionid);

        return "redirect:param.jsp";
    }


}
