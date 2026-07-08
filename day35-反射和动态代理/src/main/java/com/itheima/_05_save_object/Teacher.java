package com.itheima._05_save_object;

public class Teacher {
    private String name;
    private double salary;

    public Teacher() {
    }

    public Teacher(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Teacher{name=" + name + ", salary=" + salary + "}";
    }
}
