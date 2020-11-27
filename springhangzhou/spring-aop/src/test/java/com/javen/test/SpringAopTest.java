package com.javen.test;

import com.javen.springaop.proxy.dynamic.Calculator;
import com.javen.springaop.proxy.dynamic.ICalculator;
import com.javen.springaop.proxy.dynamic.MyInvocationHandler;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class SpringAopTest {

    @Test
    public void test01() {
        ICalculator calculator = new Calculator();
        ICalculator object = (ICalculator)createProxy(calculator);
        object.add(1, 2);
    }

//    @Test
    //公共方法生成一个代理对象
    public static Object createProxy(Object object) {
        ClassLoader classLoader = object.getClass().getClassLoader();
        Class<?>[] interfaces = object.getClass().getInterfaces();
        //传入被代理的对象
        InvocationHandler handler = new MyInvocationHandler(object);

        //动态创建代理类
        Object obeject =  Proxy.newProxyInstance(classLoader, interfaces, handler);
        //System.out.println(obeject.getClass());

        //调用代理对象
        return obeject;
    }
}
