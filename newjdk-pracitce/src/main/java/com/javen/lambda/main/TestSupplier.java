package com.javen.lambda.main;

import java.util.function.Supplier;

public class TestSupplier {
    private static String newString(Supplier<String> supplier) {
        return supplier.get();
    }

    public static void main(String[] args) {
        String str1 = newString(() -> {
            return "jinwei";
        });
        System.out.println(str1);

        String str2 = newString(() -> "jinwei supplier");
        System.out.println(str2);


    }
}
