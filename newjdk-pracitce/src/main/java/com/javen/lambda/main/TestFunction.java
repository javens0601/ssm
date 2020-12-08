package com.javen.lambda.main;

import javax.swing.text.StyledEditorKit;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class TestFunction {

    public static void main(String[] args) {
        Consumer<Integer> consumer = con -> System.out.println(con);
        consumer.accept(100);

        //方法引用-对象::实例名称
        Consumer<String> con2 = System.out::println;
        con2.accept("javen");

        //方法引用-类名::静态方法名
        BiFunction<Integer, Integer, Integer> biFunction = (x, y) -> Integer.compare(x, y);
        //BiFunction<Integer, Integer, Integer> biFunction1 = (x, y) -> Integer::compare;

        //实例::方法名
        BiFunction<String, String, Boolean> biFunction2 = (str1, str2) -> str1.equals(str2);
        BiFunction<String, String, Boolean> biFunction3 = String::equals;
        System.out.println(biFunction2.apply("123", "345"));
        System.out.println(biFunction3.apply("123", "345"));

        Supplier<String> supplier = () -> new String();
        supplier.get();
        Supplier<String> supplier1 = String::new;
        supplier1.get();

        Function<String, Integer> fun = x -> new Integer(x);

        Function<String, Integer> fun2 = Integer::new;
        fun2.apply("100");

    }


}
