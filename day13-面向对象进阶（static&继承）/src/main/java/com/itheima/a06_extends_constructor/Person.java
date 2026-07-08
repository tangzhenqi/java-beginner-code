package com.itheima.a06_extends_constructor;

public class Person {
    String name;
    int age;

    public Person() {
        System.out.println("Person() 父类无参构造");
    }

    public Person(String name, int age) {
        System.out.println("Person(String,int) 父类带参构造");
        this.name = name;
        this.age = age;
    }
}
