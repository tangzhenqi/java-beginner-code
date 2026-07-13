package com.itheima.mytest;

import java.lang.reflect.Method;

/**
 * 实战案例一：模拟 JUnit —— 用反射解析 @MyTest 注解并自动执行
 * <p>
 * 这正是「自定义注解 + 反射解析」的经典组合，也是各种框架底层做的事：
 * 注解本身没有功能，功能全靠“谁来解析它、解析到之后做什么”。
 */
public class MyTestRunner {
    public static void main(String[] args) throws Exception {
        // 1. 拿到目标类的 Class 对象
        Class<MyTestMethods> clazz = MyTestMethods.class;
        Object obj = clazz.getDeclaredConstructor().newInstance();

        // 2. 获取所有方法
        Method[] methods = clazz.getDeclaredMethods();

        int count = 0;
        for (Method method : methods) {
            // 3. 判断方法上有没有 @MyTest 注解（注解必须是 RUNTIME 才读得到）
            if (method.isAnnotationPresent(MyTest.class)) {
                method.setAccessible(true);
                method.invoke(obj);   // 4. 有就执行
                count++;
            }
        }
        System.out.println("共自动执行了 " + count + " 个 @MyTest 方法");
    }
}
