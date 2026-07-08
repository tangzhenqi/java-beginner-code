package com.itheima._01_generics;

public class _02_GenericClass {
    public static void main(String[] args) {
        //  E 在创建对象时被具化为 String
        MyArrayList<String> list1 = new MyArrayList<>();
        list1.add("aaa");
        list1.add("bbb");
        list1.add("ccc");
        // list1.add(123);          // 编译错误
        System.out.println(list1);  // [aaa, bbb, ccc]

        //  E 在创建对象时被具化为 Integer
        MyArrayList<Integer> list2 = new MyArrayList<>();
        list2.add(123);
        list2.add(456);
        int first = list2.get(0);   // 自动拆箱，不用强转
        System.out.println("first = " + first);
        System.out.println(list2);
    }
}
