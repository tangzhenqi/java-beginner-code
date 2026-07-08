package com.itheima.a01_static_basic;

/**
 * static 成员变量"共享"特性演示。
 *
 * 推荐通过 "类名.属性" 访问静态成员（虽然对象也能访问，但容易让阅读者误以为是实例属性）。
 */
public class Demo {
    public static void main(String[] args) {
        // 通过类名给静态变量赋值一次，所有对象都能看到
        Student.teacherName = "阿玮老师";

        Student s1 = new Student("张三", 23, "男");
        Student s2 = new Student("李四", 24, "女");

        s1.show();
        s2.show();

        // 任意对象改一次，所有对象都"被改了"——这才是 static 的本质
        Student.teacherName = "黑马老师";
        System.out.println("---- 切换老师后 ----");
        s1.show();
        s2.show();
    }
}
