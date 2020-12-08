package com.javen.lambda.main;

import java.io.FileInputStream;

public class TestMyClassLoader {
    static class MyClassLoader extends ClassLoader {
        private String classpath;

        public MyClassLoader(String classpath) {
            this.classpath = classpath;
        }
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] data = loadByte(name);
                return defineClass(name, data, 0, data.length);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }

        private byte[] loadByte(String name) throws Exception{
            name = name.replaceAll("\\.", "/");
            FileInputStream fis = new FileInputStream(classpath + "/" + name + ".class");
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;
        }
    }

    public static void main(String[] args) {

    }
}
