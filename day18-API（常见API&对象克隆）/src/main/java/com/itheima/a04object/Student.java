package com.itheima.a04object;

import java.util.Objects;

/**
 * 演示用 JavaBean —— 重写了 toString / equals / hashCode。
 */
public class Student {
    private String name;
    private int age;

    public Student() {}
    public Student(String name, int age) { this.name = name; this.age = age; }

    public String getName()        { return name; }
    public void setName(String n)  { this.name = n; }
    public int getAge()            { return age; }
    public void setAge(int a)      { this.age = a; }

    @Override
    public String toString() {
        return "Student{name=" + name + ", age=" + age + "}";
    }

    /**
     * equals 模板（IDE 自动生成）：
     *   1. 同一个引用直接返回 true
     *   2. 参数为 null 或运行时类型不一致返回 false
     *   3. 强转后逐属性比较
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student s = (Student) o;
        return age == s.age && Objects.equals(name, s.name);
    }

    /**
     * hashCode 必须与 equals 一致：equals 返回 true 的两个对象，hashCode 必相同。
     * 反之不要求。集合（HashMap/HashSet）依赖此契约。
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
