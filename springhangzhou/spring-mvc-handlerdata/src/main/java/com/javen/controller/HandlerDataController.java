package com.javen.controller;

import com.javen.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/handlerData")
public class HandlerDataController {

    @GetMapping("/handler01")
    public String handler01(@Valid User user, BindingResult result) {

        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            fieldErrors.forEach(e -> {
                System.out.println(e.getDefaultMessage());
            });
        }
        System.out.println(user);
        return "index";
    }
}
