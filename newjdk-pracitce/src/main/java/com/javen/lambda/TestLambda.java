package com.javen.lambda;

import java.util.Date;

@FunctionalInterface
public interface TestLambda {
    //@FunctionalInterface 注解会检测接口是不是只有一个抽象方法，还可以由默认方法和静态
    public void test();
    //默认方法
    default String name() {return "jinwei";}
    default Date birthday() {return new Date("20201202");}
    //静态方法
    static String address() {return "xianyang";}
    static int age() {return 26;}

}
