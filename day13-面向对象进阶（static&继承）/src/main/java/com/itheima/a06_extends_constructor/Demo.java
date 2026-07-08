package com.itheima.a06_extends_constructor;

public class Demo {
    public static void main(String[] args) {
        System.out.println("==== new Student() ====");
        new Student();

        System.out.println("==== new Student(name, age) ====");
        new Student("张三", 23);

        System.out.println("==== new Student(name, age, school) ====");
        Student s = new Student("李四", 24, "传智大学");
        System.out.println("name=" + s.name + ", age=" + s.age + ", school=" + s.school);
    }
}
