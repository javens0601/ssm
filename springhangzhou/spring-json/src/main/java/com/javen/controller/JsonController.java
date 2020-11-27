package com.javen.controller;

import com.javen.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 1 加入jackson的依赖
 * 2 将依赖的包加入lib目录下
 * 3 在对应的方法上面加上 @ResponseBody
 */
//@Controller
@RestController
@RequestMapping("/json")
public class JsonController {

    @RequestMapping("/response01")
    //将返回值作为文本进行返回了，并不是返回逻辑视图名称了
    @ResponseBody
    public User response01(@RequestBody int age, @RequestBody Date date) {
        Map<String, String> hobby = new HashMap<String, String>(8);
        hobby.put("1", "游泳");
        hobby.put("2", "骑车");
        User user = new User();
        user.setAge(age);
        user.setName("靳威");
        user.setHobby(hobby);
        user.setDate(date);
        System.out.println("return json : " +  user);
        return user;
    }
}
