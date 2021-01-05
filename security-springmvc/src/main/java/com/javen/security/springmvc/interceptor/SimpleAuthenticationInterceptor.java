package com.javen.security.springmvc.interceptor;

import com.javen.security.springmvc.model.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class SimpleAuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //校验用户请求的url是否在用户的权限范围内
        Object attribute = request.getSession().getAttribute(UserDto.SESSION_KEY);
        if (null == attribute) {
            writeContent(response, "请登陆");
        }

        UserDto userDto = (UserDto) attribute;
        String reqUrl = request.getRequestURI();
        if (userDto.getAuthorities().contains("p1") && reqUrl.contains("/r/r1")) {
            return true;
        } else if (userDto.getAuthorities().contains("p2") && reqUrl.contains("/r/r2")) {
            return true;
        }
        writeContent(response, "拒绝访问");
        return false;
    }

    private  void writeContent(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.print(msg);
        printWriter.close();
    }
}
