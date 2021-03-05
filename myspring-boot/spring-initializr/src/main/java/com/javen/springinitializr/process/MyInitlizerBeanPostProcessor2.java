package com.javen.springinitializr.process;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class MyInitlizerBeanPostProcessor2 implements BeanPostProcessor, Ordered {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("自定义 order 2  BeforeInitialization --- " + bean);
        System.out.println("自定义 order 2  BeforeInitialization --- " + beanName);
        System.out.println("\n-------------------------------------------------");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("自定义 order 2  AfterInitialization --- " + bean);
        System.out.println("自定义 order 2  AfterInitialization --- " + beanName);
        System.out.println("\n-------------------------------------------------");
        return bean;
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
