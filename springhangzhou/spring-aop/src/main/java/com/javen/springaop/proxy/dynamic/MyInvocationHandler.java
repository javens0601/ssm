package com.javen.springaop.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
    //被代理的对象
    Object object;

    public MyInvocationHandler(Object object) {
        this.object = object;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("方法增强前");
        //执行被代理的真实方法
        Object result = method.invoke(object, args);
        System.out.println("方法增强后");
        return result;
    }
}
