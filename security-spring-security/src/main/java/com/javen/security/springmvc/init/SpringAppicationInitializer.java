package com.javen.security.springmvc.init;

import com.javen.security.springmvc.config.ApplicationConfig;
import com.javen.security.springmvc.config.WebConfig;
import com.javen.security.springmvc.config.WebSecurityConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringAppicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        //指定rootContext的配置类
        //return new Class[0];
        return new Class<?>[] {ApplicationConfig.class, WebSecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        //指定servletContext的配置类
        //return new Class[0];
        return new Class<?>[] {WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
