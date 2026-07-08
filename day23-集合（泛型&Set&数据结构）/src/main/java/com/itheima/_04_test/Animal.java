package com.itheima._04_test;

public abstract class Animal {
    private String name;
    private int age;

    public Animal() {}
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }

    /** 每个具体动物自己实现"吃什么、怎么吃"。 */
    public abstract void eat();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{name=" + name + ", age=" + age + "}";
    }
}
