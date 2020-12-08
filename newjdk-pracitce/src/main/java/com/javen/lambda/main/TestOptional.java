package com.javen.lambda.main;

import com.sun.deploy.util.StringUtils;

import java.util.Optional;
import java.util.function.Supplier;

public class TestOptional {
    public static void main(String[] args) {
        String javen = "jinwei";
        String javennull = null;
        //Optional.of(javen).ifPresent(System.out::println);
        //System.out.println(Optional.of(javennull).filter((String::isEmpty)));
        Optional.ofNullable(javennull).orElseThrow(() -> new IndexOutOfBoundsException());
        //Optional.of(javennull).filter((String::isEmpty)).orElseThrow(() -> new NullPointerException());
    }
}
