package com.javen.pattern.singleton;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingletonClient {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i =0; i < 10; i++) {
            executor.execute(() -> {
                try {
                    //懒汉
                    //SingletonLazy singletonLazy = SingletonLazy.getInstance();

                    //恶汉
                    //SingletonActive singletonActive = SingletonActive.getInstance();

                    //枚举
                    SingletonEnum singletonEnum = SingletonEnum.INSTANCE;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
    }
}
