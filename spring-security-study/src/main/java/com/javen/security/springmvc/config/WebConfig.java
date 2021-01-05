package com.javen.security.springmvc.config;

import com.javen.security.springmvc.interceptor.SimpleAuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//相当于springmvc.xml
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.javen.security.springmvc",
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)})
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SimpleAuthenticationInterceptor authenticationInterceptor;

    //需要配置视图解析器
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    //增加jsp
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
    }

    //增加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //只对配置需要拦截的目录进行拦截
        registry.addInterceptor(authenticationInterceptor).addPathPatterns("/r/**");
    }
}
