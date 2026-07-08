package com.summary.multi;

/**
 * 一个普通的"类"，用来演示"多对象互不影响"。
 */
public class GirlFriend {
    String name;
    int age;
    String gender;

    public void sleep() {
        System.out.println(name + " 在睡觉");
    }

    public void eat() {
        System.out.println(name + " 在吃饭");
    }
}
