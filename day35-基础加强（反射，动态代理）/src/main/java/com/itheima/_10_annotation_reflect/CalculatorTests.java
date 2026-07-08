package com.itheima._10_annotation_reflect;

/**
 * 一个"被测试类"，里面混杂着普通方法和打了 @MyTest 的方法。
 * 通过 TestRunner 的反射扫描，只会运行带注解的那几个方法 —— 这就是
 * JUnit 早期版本的核心机制。
 */
public class CalculatorTests {

    @MyTest("加法应当正确")
    public void testAdd() {
        int r = 1 + 2;
        if (r != 3) throw new AssertionError("1+2 != 3");
    }

    @MyTest("演示一个会失败的用例")
    public void testFail() {
        throw new IllegalStateException("故意失败用来看效果");
    }

    // 没贴注解 —— 应该被跳过
    public void helperShouldBeIgnored() {
        System.out.println("不应当被调用！");
    }
}
