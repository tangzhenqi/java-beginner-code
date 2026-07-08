package com.itheima._02_constructor;

public class Person {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    protected Person(int age) {
        this.age = age;
    }

    private Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{name = " + name + ", age = " + age + "}";
    }
}
