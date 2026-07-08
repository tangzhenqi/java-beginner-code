package com.summary.javabean;

/**
 * 演示 JavaBean 的两种创建方式：
 *   1. 空参构造 + setter 逐个赋值
 *   2. 全参构造 直接赋值
 */
public class JavaBeanDemo {
    public static void main(String[] args) {
        // 方式 1：空参构造 + setter
        Student s1 = new Student();
        s1.setId(1);
        s1.setName("张三");
        s1.setAge(20);
        System.out.println(s1.getId() + ", " + s1.getName() + ", " + s1.getAge());

        // 方式 2：全参构造
        Student s2 = new Student(2, "李四", 22);
        System.out.println(s2.getId() + ", " + s2.getName() + ", " + s2.getAge());
    }
}
