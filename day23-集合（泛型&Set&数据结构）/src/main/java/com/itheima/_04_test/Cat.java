package com.itheima._04_test;

/**
 * Cat 仍是抽象类：因为不同品种的猫"吃什么"不一样，由子类决定。
 */
public abstract class Cat extends Animal {
    public Cat() {}
    public Cat(String name, int age) { super(name, age); }
}
