package com.itheima._06_reflect_config;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 反射 + 配置文件。
 *
 * 在不修改代码的前提下，仅通过修改 prop.properties，就能让程序换一个类、
 * 换一个方法去执行 —— 这是各种"插件机制 / 框架 IOC"的最朴素雏形。
 *
 * 资源加载方式：用类加载器 getResourceAsStream("xxx") 从 classpath 读取，
 * 这样不依赖工作目录，IDE / mvn package 后都能正常工作。
 */
public class ReflectConfigDemo {

    public static void main(String[] args) throws Exception {

        // 1. 从 classpath 加载配置
        Properties prop = new Properties();
        try (InputStream is = ReflectConfigDemo.class
                .getClassLoader()
                .getResourceAsStream("prop.properties")) {
            prop.load(is);
        }
        System.out.println("配置内容: " + prop);

        String className = prop.getProperty("classname");
        String methodName = prop.getProperty("method");

        // 2. 反射创建对象
        Class<?> clazz = Class.forName(className);
        Constructor<?> con = clazz.getDeclaredConstructor();
        Object obj = con.newInstance();

        // 3. 反射调用方法
        Method method = clazz.getDeclaredMethod(methodName);
        method.setAccessible(true);
        method.invoke(obj);
    }
}
