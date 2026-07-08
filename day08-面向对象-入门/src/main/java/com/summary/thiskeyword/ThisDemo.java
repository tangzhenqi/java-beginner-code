package com.summary.thiskeyword;

public class ThisDemo {
    public static void main(String[] args) {
        Person p = new Person();
        p.method();          // age = 0  (int 默认值)
        p.setAge(25);
        System.out.println("赋值后：" + p.getAge());   // 25
    }
}
