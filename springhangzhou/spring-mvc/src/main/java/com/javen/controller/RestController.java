package com.javen.controller;

import com.javen.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/rest")
public class RestController {

    @GetMapping("user/{id}")
    public String query(User user) {
        System.out.println("查询" + user);

        return "/rest.jsp";
    }

    @PostMapping("user/{id}")
    public String add(User user) {
        System.out.println("新增" + user);

        return "redirect:/rest.jsp";
    }

    @PutMapping("user/{id}")
    public String update(User user) {
        System.out.println("修改" + user);

        return "redirect:/rest.jsp";
    }

    @DeleteMapping("user/{id}")
    public String delete(User user) {
        System.out.println("删除" + user);

        return "redirect:/rest.jsp";
    }


    @GetMapping("model/{id}")
    public ModelAndView getUserInfo(String id) {
        System.out.println(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/rest.jsp");
        modelAndView.addObject("name", id);
        return modelAndView;
    }

}
