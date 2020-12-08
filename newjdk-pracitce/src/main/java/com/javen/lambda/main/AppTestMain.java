package com.javen.lambda.main;

import com.javen.lambda.TestLambda;
import com.javen.lambda.impl.TestLambdaImpl;

public class AppTestMain {

    public static void main(String[] args) {
        //传递实现类
        mainTest(TestLambdaImpl::new);
        //匿名内部类
        mainTest(new TestLambda() {
            @Override
            public void test() {
                System.out.println("匿名内部类实现 。。。");
            }
        });

        //lambda实现
        mainTest(() -> {
            System.out.println("lambda 实现");
        });
        //当lambda表达式只包含一条语句的时候，可以省略大括号，return，语句结尾的分号
        mainTest(() -> System.out.println("lambda 简单实现"));
    }

    private static void mainTest(TestLambda testLambda) {
        testLambda.test();
    }

}
