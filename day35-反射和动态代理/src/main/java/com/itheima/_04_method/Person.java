package com.itheima._04_method;

import java.io.IOException;

public class Person {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void sleep() {
        System.out.println("睡觉");
    }

    @SuppressWarnings("unused")
    private String eat(String something) throws IOException, NullPointerException {
        System.out.println("在吃 " + something);
        return "好吃，奥利给";
    }

    @SuppressWarnings("unused")
    private void eat(String something, int times) {
        System.out.println("一共吃了 " + times + " 份 " + something);
    }

    @Override
    public String toString() {
        return "Person{name = " + name + ", age = " + age + "}";
    }
}
