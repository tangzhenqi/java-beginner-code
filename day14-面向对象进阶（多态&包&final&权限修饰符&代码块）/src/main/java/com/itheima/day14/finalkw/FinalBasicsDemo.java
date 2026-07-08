package com.itheima.day14.finalkw;

/**
 * final 三种用法 —— 基础演示：
 *
 *  1. final 修饰变量 —— "常量"，只能被赋值一次
 *  2. final 修饰方法 —— 不能被重写
 *  3. final 修饰类   —— 不能被继承
 *
 * 命名约定：
 *      final 修饰的常量，一般使用 全大写 + 下划线 命名，如 MAX_COUNT、PI
 */
public class FinalBasicsDemo {

    /** static final 常量 —— 类级别共享，写法上一般在声明时直接赋值。 */
    public static final double PI = 3.14;

    public static void main(String[] args) {
        // final 局部变量：只能赋值一次
        final int a = 10;
        // a = 20;     // 编译失败：Cannot assign a value to final variable 'a'
        System.out.println("a = " + a + ", PI = " + PI);

        // final 方法：子类只能调用、不能重写
        FinalChild child = new FinalChild();
        child.show();    // 来自 FinalParent 的 final 方法
        child.hello();   // 子类重写后的版本

        // final 类：可以正常使用，但不能 extends
        SealedBox box = new SealedBox();
        box.open();
    }
}
