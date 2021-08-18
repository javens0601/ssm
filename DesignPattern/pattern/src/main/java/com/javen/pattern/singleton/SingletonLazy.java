package com.javen.pattern.singleton;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class SingletonLazy {
    private SingletonLazy() {
        System.out.println("构造函数");
    }

    private static volatile SingletonLazy instance = null;
    private static ReentrantLock lock = new ReentrantLock();

    public static SingletonLazy getInstance() throws InterruptedException {
        if (null == instance) {
            /*
            synchronized (SingletonLazy.class) {
                if (null == instance) {
                    Thread.sleep(10);
                    instance = new SingletonLazy();
                }
            }*/

            //lock.tryLock(50, TimeUnit.MILLISECONDS);
            lock.lock();
            if (null == instance) {
                Thread.sleep(100);
                instance = new SingletonLazy();
            }
            lock.unlock();
            }
        return instance;
    }

    @Override
    public String toString() {
        return "SingletonLazy{}";
    }
}
