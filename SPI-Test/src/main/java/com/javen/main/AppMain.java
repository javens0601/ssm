package com.javen.main;

import com.javen.face.DemoFace;
import com.javen.face.impl.DemoFacePlusImpl;

import java.util.Iterator;
import java.util.ServiceLoader;

public class AppMain {
    public static void main(String[] args) {
        DemoFace demoFace = new DemoFacePlusImpl();
        String res1 = demoFace.plus(1,2);
        System.out.println(res1);

        //测试spi
        //可以不需要改动jar包就对jar中的接口做实现，插件化
        //spi的缺点。spi会把文件中定义的都给加载，效率不高
        ServiceLoader<DemoFace> load = ServiceLoader.load(DemoFace.class);
        Iterator<DemoFace> iterator = load.iterator();
        while (iterator.hasNext()) {
            DemoFace demoFace1 = iterator.next();
            String res2 = demoFace1.plus(2, 3);
            System.out.println(res2);
        }
    }
}
