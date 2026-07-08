package com.itheima.day14.codeblock;

/**
 * 拓展演示：继承体系下，代码块和构造方法的执行顺序。
 *
 * 期望输出顺序：
 *      Parent - 静态代码块
 *      Child  - 静态代码块
 *      Parent - 构造代码块（实例初始化）
 *      Parent - 构造方法
 *      Child  - 构造代码块（实例初始化）
 *      Child  - 构造方法
 *      ---- 第二次 new ----
 *      Parent - 构造代码块（实例初始化）
 *      Parent - 构造方法
 *      Child  - 构造代码块（实例初始化）
 *      Child  - 构造方法
 *
 * 重点：静态代码块只在"首次类加载"时执行一次；
 *      构造代码块每次 new 都会执行，且永远早于构造方法体。
 */
public class InheritanceOrderDemo {
    public static void main(String[] args) {
        new Child();
        System.out.println("---- 第二次 new ----");
        new Child();
    }
}
