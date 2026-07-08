package com.itheima._01_generics;

public class _04_GenericInterface {
    public static void main(String[] args) {
        // 方式一：实现类已经把类型钉死成 String
        StringMyListImpl strList = new StringMyListImpl();
        strList.add("hello");
        strList.add("world");
        System.out.println(strList.get(0));

        // 方式二：实现类延续了泛型，调用方自由决定具体类型
        GenericMyListImpl<Integer> intList = new GenericMyListImpl<>();
        intList.add(100);
        intList.add(200);
        System.out.println(intList.get(1));

        GenericMyListImpl<Double> doubleList = new GenericMyListImpl<>();
        doubleList.add(3.14);
        System.out.println(doubleList.get(0));
    }
}
