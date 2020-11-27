package com.javen.springaop.proxy.dynamic;

public class Calculator implements ICalculator{
    public int add(int a, int b) {
        System.out.println("方法执行。。。");
        return a + b;
    }
}
