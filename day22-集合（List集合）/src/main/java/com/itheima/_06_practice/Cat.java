package com.itheima._06_practice;

/** 猫科（仍然抽象，把 eat 的实现推给具体品种）。 */
public abstract class Cat extends Animal {
    public Cat() {}
    public Cat(String name, int age) { super(name, age); }
}
