package com.itheima._05_generics;

import java.util.ArrayList;

/**
 * 知识点二十一（拓展）：泛型擦除（Type Erasure）。
 *
 *  - 编译后，泛型类型参数会被擦除：
 *      ArrayList<String> -> ArrayList（运行时只看到原生类型）
 *  - 因此：
 *      ① 运行时不能直接知道 List 里的 E 是什么类型；
 *      ② 一个类不能"用泛型参数区别重载"，
 *         如 void f(List<String>) 和 void f(List<Integer>) 算同一个方法；
 *      ③ getClass() 比较时，两个不同泛型实例化的对象，class 对象其实是同一个。
 */
public class _13_GenericErasureDemo {
    public static void main(String[] args) {
        ArrayList<String> sl = new ArrayList<>();
        ArrayList<Integer> il = new ArrayList<>();

        System.out.println("ArrayList<String>.getClass() == ArrayList<Integer>.getClass()? "
                + (sl.getClass() == il.getClass()));
        // 输出 true —— 因为运行时它们都是同一个 ArrayList.class
    }
}
