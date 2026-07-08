package com.itheima.day14.codeblock;

/**
 * 配合 InheritanceOrderDemo 使用 —— 父类。
 *
 * 类加载时的初始化顺序口诀（含继承）：
 *      父类静态  ->  子类静态  ->  父类实例(构造块)  ->  父类构造方法
 *                                 ->  子类实例(构造块)  ->  子类构造方法
 */
public class Parent {

    static {
        System.out.println("Parent - 静态代码块");
    }

    {
        System.out.println("Parent - 构造代码块（实例初始化）");
    }

    public Parent() {
        System.out.println("Parent - 构造方法");
    }
}
