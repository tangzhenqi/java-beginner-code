package com.itheima._04_arraylist;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 知识点十二：ArrayList 是 List 接口最常用的实现，底层是"动态数组"。
 *
 *  常用 API 全部继承自 List/Collection，演示一遍即可。
 *  ArrayList 的迭代器返回值类型是 Iterator<E>，遍历方式与 Collection 完全一致。
 */
public class ArrayListBasicDemo {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
