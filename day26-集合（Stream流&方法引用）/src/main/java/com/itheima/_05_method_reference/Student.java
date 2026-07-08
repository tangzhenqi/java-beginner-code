package com.itheima._05_method_reference;

/**
 * 演示用学生类。
 * 为方法引用的「构造方法」与「实例方法」用法做铺垫。
 */
public class Student {
    private String name;
    private int age;

    public Student() {
    }

    /**
     * 单参构造：接受形如 "张三,23" 的字符串，便于 {@code Student::new} 方法引用。
     */
    public Student(String text) {
        String[] split = text.split(",");
        this.name = split[0];
        this.age = Integer.parseInt(split[1]);
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{name=" + name + ", age=" + age + "}";
    }
}
