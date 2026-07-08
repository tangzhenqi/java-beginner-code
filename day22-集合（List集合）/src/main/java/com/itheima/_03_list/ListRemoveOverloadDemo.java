package com.itheima._03_list;

import java.util.ArrayList;
import java.util.List;

/**
 * 知识点九：List.remove(...) 的重载陷阱（面试常考）。
 *
 *  List 同时提供了两个 remove：
 *     E       remove(int index)
 *     boolean remove(Object o)
 *
 *  对于 List<Integer>，list.remove(1) 编译时优先匹配实参类型完全一致的方法，
 *  也就是 remove(int)，会删除"索引 1"上的元素，而不是值为 1 的元素。
 *
 *  想删值，必须先把 int 装箱成 Integer：
 *      list.remove(Integer.valueOf(1));
 */
public class ListRemoveOverloadDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        // 按索引删除（删的是 0/1/2 索引上的元素）
        List<Integer> a = new ArrayList<>(list);
        a.remove(1); // 删的是索引 1，即数字 2
        System.out.println("a.remove(1) -> 按索引: " + a);

        // 按值删除：手动装箱
        List<Integer> b = new ArrayList<>(list);
        b.remove(Integer.valueOf(1)); // 删的是值 1
        System.out.println("b.remove(Integer.valueOf(1)) -> 按值: " + b);
    }
}
