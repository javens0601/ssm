package com.javen.pattern.singleton;

public enum SingletonEnum {
    INSTANCE;

    public void doSome() {
        System.out.println("枚举单例实现");
    }
}
