package com.javen.lambda.main;

import java.util.function.Consumer;

public class TestConsumer {

    public static void main(String[] args) {
        String name = "javen";
        consumer(name, come -> System.out.println(come));

        doubleConsumer(name, come -> System.out.println(come + " 1 "), come -> System.out.println(come + " 2 "));
    }

    public static void consumer(String come, Consumer<String> consumer) {
        consumer.accept(come);
    }
    public static void doubleConsumer(String come, Consumer<String> consumer1, Consumer<String> consumer2) {
        consumer1.andThen(consumer2).accept(come);
    }
}
