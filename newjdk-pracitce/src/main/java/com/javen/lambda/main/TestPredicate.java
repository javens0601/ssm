package com.javen.lambda.main;

import java.util.function.Predicate;

public class TestPredicate {
    public static void main(String[] args) {

        String name = "jinwei";
        boolean healthy = predciate("javen", some -> {
            System.out.println(some);
            System.out.println(name);
           return name.equals(some);
        });
        System.out.println(healthy);

        //andPredciate();
    }

    private static boolean predciate(String some, Predicate<String> predicate) {
        return predicate.test(some);
    }

    //与
    private static boolean andPredciate(String some, Predicate<String> predicate1, Predicate<String> predicate2) {
        return predicate1.and(predicate2).test(some);
    }

    //或
    private static boolean orPredciate(String some, Predicate<String> predicate1, Predicate<String> predicate2) {
        return predicate1.or(predicate2).test(some);
    }

    //非
    private static boolean negPredciate(String some, Predicate<String> predicate) {
        return predicate.negate().test(some);
    }
}
