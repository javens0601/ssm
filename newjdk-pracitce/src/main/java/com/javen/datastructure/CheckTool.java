package com.javen.datastructure;

public class CheckTool {
    public static void check(String name, MyTask task) {
        System.out.println(name + " -- before");
        task.execute();
        System.out.println(name + " -- after");
    }
}

interface MyTask {
    void execute();
}
