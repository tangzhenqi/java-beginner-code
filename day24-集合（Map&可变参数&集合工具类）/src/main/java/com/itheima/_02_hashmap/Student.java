package com.itheima._02_hashmap;

import java.util.Objects;

/**
 * HashMap 自定义键 JavaBean。
 *
 * 关键点：作为 HashMap 的 key 时必须重写 hashCode 和 equals，
 * 否则同姓名同年龄的两个对象也会被当成不同的 key。
 */
public class Student {
    private String name;
    private int age;

    public Student() {}

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public void   setName(String name) { this.name = name; }
    public int    getAge()  { return age; }
    public void   setAge(int age) { this.age = age; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student s = (Student) o;
        return age == s.age && Objects.equals(name, s.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Student{name=" + name + ", age=" + age + "}";
    }
}
