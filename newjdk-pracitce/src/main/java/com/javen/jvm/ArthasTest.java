package com.javen.jvm;

public class ArthasTest {
    public static void main(String[] args) {
        cpuHigh();

        deadLock();

        System.out.println("main thread end ...");

    }

    //模拟cpu飙高
    private static void cpuHigh() {
        new Thread(() -> {
            while (true) {

            }
        }).start();
    }

    //模拟死锁
    private static void deadLock() {
        Object objectA = new Object();
        Object objectB = new Object();

        new Thread(() -> {
            synchronized (objectA) {
                System.out.println("get resource A");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("waiting get resource B");
                synchronized (objectB) {
                    System.out.println("get resource B");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (objectB) {
                System.out.println("get resource B");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("waiting get resource A");
                synchronized (objectA) {
                    System.out.println("get resource A");
                }
            }
        }).start();

    }
}
