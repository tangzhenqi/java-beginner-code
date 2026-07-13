package com.itheima;

/**
 * 演示一：三级类加载器与双亲委派
 * <p>
 * System（应用类加载器）-> Platform（平台类加载器）-> Bootstrap（启动类加载器，显示为 null）
 */
public class ClassLoaderDemo {
    public static void main(String[] args) {
        // 系统类加载器：加载 classpath（我们自己写的类）
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        // 它的父加载器：平台类加载器（加载 JDK 部分模块）
        ClassLoader platformClassLoader = systemClassLoader.getParent();
        // 再往上：启动类加载器，用 C++ 实现，Java 里拿到的是 null
        ClassLoader bootstrapClassLoader = platformClassLoader.getParent();

        System.out.println("系统类加载器  ：" + systemClassLoader);
        System.out.println("平台类加载器  ：" + platformClassLoader);
        System.out.println("启动类加载器  ：" + bootstrapClassLoader);

        System.out.println("---- 不同类由谁加载 ----");
        // 我们自己写的类 -> 系统类加载器
        System.out.println("ClassLoaderDemo 由：" + ClassLoaderDemo.class.getClassLoader());
        // JDK 核心类 String -> 启动类加载器（null）
        System.out.println("String 由        ：" + String.class.getClassLoader());
    }
}
