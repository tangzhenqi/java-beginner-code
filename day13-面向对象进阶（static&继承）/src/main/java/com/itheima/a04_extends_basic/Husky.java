package com.itheima.a04_extends_basic;

/**
 * 多层继承示例：Husky -> Dog -> Animal。
 * Java 是单继承：一个类只能 extends 一个父类，但允许多层继承形成"继承链"。
 */
public class Husky extends Dog {
    public void breakHome() {
        System.out.println("哈士奇在拆家");
    }
}
