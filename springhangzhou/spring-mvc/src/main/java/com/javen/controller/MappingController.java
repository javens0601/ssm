package com.javen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @RequestMapping 用来处理url映射，
 * 还可以用在类上上面，将请求url模块化
 * 避免请求方法中的映射重复
 *
 *      param:参数校验
 *      headers:请求头必须包含某个值
 *      consumers:当前请求内容必须为指定值
 *      produces:设置当前响应内容类型
 *
 *  映射的url支持通配符
 *      ?       一个问号匹配一个字符
 *      *       一个*匹配人一个字符
 *      **      匹配任意字符，任意层次
 */
@Controller
@RequestMapping("/mapping")
public class MappingController {

    @RequestMapping(value = "hello01", method = RequestMethod.POST)
    public String helloMapping( String username) {
        System.out.println("maping success ！" + username);

        return "/index.jsp";
    }


    @GetMapping("hello02/{name}/{id}")
    public String helloMapping1(@PathVariable("name")String username, @PathVariable("id")String id) {
        System.out.println("maping success ！" + username + "  " + id);

        return "/index.jsp";
    }
}
