package com.javen.lambda.main;

import com.sun.crypto.provider.DESCipher;

public class TestJdkClassLoader {
    public static void main(String[] args) throws Exception{
        //引导类加载器
        System.out.println(String.class.getClassLoader());
        //扩展类加载器
        System.out.println(DESCipher.class.getClassLoader());
        //应用类加载器
        System.out.println(TestJdkClassLoader.class.getClassLoader());


        System.out.println();
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        //获取appClassLoader 的父加载器
        ClassLoader extClassLoader = appClassLoader.getParent();
        //获取extClassloader 的父加载器
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();

        System.out.println(bootstrapClassLoader);
        System.out.println(extClassLoader);
        System.out.println(appClassLoader);

    }
}
