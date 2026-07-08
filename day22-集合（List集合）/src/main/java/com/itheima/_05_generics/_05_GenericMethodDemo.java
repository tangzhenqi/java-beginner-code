package com.itheima._05_generics;

import java.util.ArrayList;

/**
 * 知识点十七（演示）：调用泛型方法。
 *
 *  调用泛型方法时，E 由实参自动推断。同一个 _04_ListUtil.addAll
 *  既能往 ArrayList<String> 加字符串，也能往 ArrayList<Integer> 加整数。
 */
public class _05_GenericMethodDemo {
    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>();
        _04_ListUtil.addAll(list1, "aaa", "bbb", "ccc", "ddd");
        System.out.println(list1);

        ArrayList<Integer> list2 = new ArrayList<>();
        _04_ListUtil.addAll(list2, 1, 2, 3, 4);
        System.out.println(list2);
    }
}
