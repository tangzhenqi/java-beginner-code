package com.itheima._06_practice;

/**
 *           Animal (abstract)
 *          /                 \
 *        Cat (abstract)     Dog (abstract)
 *        /    \              /     \
 *  PersianCat LiHuaCat   TeddyDog  HuskyDog
 *
 *  抽象类思想：Cat/Dog 在抽象层级也是"抽象"的，
 *             具体怎么 eat 由真正的"品种"来决定。
 */
public abstract class Animal {
    private String name;
    private int age;

    public Animal() {}
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public abstract void eat();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{name=" + name + ", age=" + age + "}";
    }
}
