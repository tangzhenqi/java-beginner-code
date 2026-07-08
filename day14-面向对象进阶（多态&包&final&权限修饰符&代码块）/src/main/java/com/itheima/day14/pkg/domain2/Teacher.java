package com.itheima.day14.pkg.domain2;

/**
 * domain2 包下的 Teacher。
 * 与 domain1.Teacher 同名 —— 通过包名（全限定名）来区分。
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
        return "domain2.Teacher{name='" + name + "', age=" + age + "}";
    }
}
