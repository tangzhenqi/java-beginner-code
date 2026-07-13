package com.itheima.mytest;

/**
 * 被“测试框架”扫描的类：只有加了 @MyTest 的方法才会被自动执行。
 */
public class MyTestMethods {

    @MyTest
    public void method1() {
        System.out.println("method1 被执行了");
    }

    // 没有 @MyTest，不会被执行
    public void method2() {
        System.out.println("method2 不应该被执行");
    }

    @MyTest
    public void method3() {
        System.out.println("method3 被执行了");
    }
}
