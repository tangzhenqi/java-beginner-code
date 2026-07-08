package com.itheima.day14.polymorphism;

/**
 * 多态案例的共用父类 —— Person。
 * 多态的前提之一：必须存在继承关系（子类 extends 父类）。
 */
public class Person {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    /**
     * 父类提供 show 方法 —— 子类会通过方法重写呈现各自的形态。
     */
    public void show() {
        System.out.println(name + ", " + age);
    }
}
