package com.itheima.day14.pkg.domain1;

/**
 * domain1 包下的 Teacher。
 * 演示：当两个不同包下有同名类时，如何在同一个文件中使用它们。
 */
public class Teacher {
    private String name;
    private int age;

    public Teacher() {}

    public Teacher(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "domain1.Teacher{name='" + name + "', age=" + age + "}";
    }
}
