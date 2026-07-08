package com.itheima._05_save_object;

public class Student {
    private String name;
    private int age;
    private char gender;
    private double height;
    private String hobby;

    public Student() {
    }

    public Student(String name, int age, char gender, double height, String hobby) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "Student{name=" + name + ", age=" + age + ", gender=" + gender
                + ", height=" + height + ", hobby=" + hobby + "}";
    }
}
