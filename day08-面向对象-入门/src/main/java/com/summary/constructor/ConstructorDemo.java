package com.summary.constructor;

public class ConstructorDemo {
    public static void main(String[] args) {
        // 调用空参构造
        Student s1 = new Student();
        s1.setName("张三");
        s1.setAge(20);

        // 调用全参构造
        Student s2 = new Student("李四", 22);

        // 调用单参构造（内部用 this(name, 18) 复用全参）
        Student s3 = new Student("王五");

        System.out.println(s1.getName() + ", " + s1.getAge());
        System.out.println(s2.getName() + ", " + s2.getAge());
        System.out.println(s3.getName() + ", " + s3.getAge());  // 王五, 18
    }
}
