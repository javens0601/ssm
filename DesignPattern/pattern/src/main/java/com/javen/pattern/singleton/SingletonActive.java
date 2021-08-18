package com.javen.pattern.singleton;

public class SingletonActive {
    public SingletonActive() {
        System.out.println("构造函数");
    }

    private static SingletonActive instance = new SingletonActive();

    public static SingletonActive getInstance() {
        return instance;
    }
}
