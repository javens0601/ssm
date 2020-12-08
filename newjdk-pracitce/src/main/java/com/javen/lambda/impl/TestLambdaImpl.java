package com.javen.lambda.impl;

import com.javen.lambda.TestLambda;

public class TestLambdaImpl implements TestLambda {
    @Override
    public void test() {
        System.out.println("接口实现 FunctionalInterface");
    }
}
