package com.javen.springinitializr.process;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class MyInitlizerBeanPostProcessor implements BeanPostProcessor, Ordered {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("自定义 order 1  BeforeInitialization --- " + bean);
        System.out.println("自定义 order 1  BeforeInitialization --- " + beanName);
        System.out.println("\n-------------------------------------------------");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("自定义 order 1  AfterInitialization --- " + bean);
        System.out.println("自定义 order 1  AfterInitialization --- " + beanName);
        System.out.println("\n-------------------------------------------------");
        return bean;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
