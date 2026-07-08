package com.itheima.a01_static_basic;

/**
 * static 成员变量演示。
 *
 * 关键点：
 *  - 非 static 的成员变量（name/age/gender）：每个对象独立一份。
 *  - static 的成员变量（teacherName）：所有对象共享同一份，存放于方法区。
 *  - static 变量随类一起加载，先于任何对象存在，可用类名直接访问。
 */
public class Student {

    private String name;
    private int age;
    private String gender;

    public static String teacherName;

    public Student() {
    }

    public Student(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public void show() {
        System.out.println(name + ", " + age + ", " + gender + ", 老师=" + teacherName);
    }
}
