package com.itheima._01_generics;

import java.util.ArrayList;

/*
 * 拓展知识点：类型擦除（Type Erasure）
 *
 * Java 的泛型是"伪泛型"——只在编译期存在。
 *      编译完成后，所有的类型参数 E、T、K、V 都会被擦除：
 *          - 无界（<T>）擦除为 Object
 *          - 有界（<T extends Number>）擦除为它的上界 Number
 *
 * 由此产生的几个"看似奇怪"的现象：
 *      1. 运行时拿到的 Class 对象是相同的：
 *              ArrayList<String> 与 ArrayList<Integer> 同属 java.util.ArrayList
 *      2. 不能 new T() / new T[10]：运行时不知道 T 究竟是什么
 *      3. 不能用泛型类型做 instanceof 判断：obj instanceof List<String> 非法
 *      4. 静态字段不能使用类上的泛型 T
 *
 * 反向思考：
 *      为什么 Java 要做类型擦除而不是像 C++/C# 那样保留类型？
 *      —— 为了二进制兼容。Java 5 引入泛型时，要让旧的 .class 仍能运行，
 *         于是选择"擦除"，把泛型只当成一种编译期的语法糖。
 */
public class _06_TypeErasure {
    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();

        // 现象 1：运行时其实是同一个类
        System.out.println(list1.getClass());            // class java.util.ArrayList
        System.out.println(list2.getClass());            // class java.util.ArrayList
        System.out.println(list1.getClass() == list2.getClass()); // true

        // 现象 2：泛型方法在调用时编译器自动推断 T
        printAll(list1);
        printAll(list2);
    }

    /** 拓展：演示泛型方法的"类型推断"。 */
    private static <T> void printAll(ArrayList<T> list) {
        System.out.println("size=" + list.size());
    }
}
