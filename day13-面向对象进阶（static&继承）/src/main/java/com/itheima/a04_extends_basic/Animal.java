package com.itheima.a04_extends_basic;

/**
 * 父类：定义"共性行为"。
 *
 * 注意：子类只能继承到父类中"非 private"的成员。
 * private 成员（这里没演示）虽然存在于子对象的内存中，但子类无法直接访问。
 */
public class Animal {

    public void eat() {
        System.out.println("动物在吃东西");
    }

    public void drink() {
        System.out.println("动物在喝水");
    }
}
