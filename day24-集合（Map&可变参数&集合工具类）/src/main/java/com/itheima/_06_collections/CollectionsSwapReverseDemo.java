package com.itheima._06_collections;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Collections 其它常用工具方法。
 *
 *   swap(List<?> list, int i, int j)       交换两个位置的元素
 *   reverse(List<?> list)                   反转 List
 *   frequency(Collection, Object)           统计某元素出现的次数
 *   replaceAll(List, oldVal, newVal)        替换 List 中所有 oldVal 为 newVal
 */
public class CollectionsSwapReverseDemo {
    public static void main(String[] args) {
        // swap
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 2, 3);
        Collections.swap(list, 0, 2);
        System.out.println("swap     : " + list);

        // reverse
        ArrayList<Integer> list2 = new ArrayList<>();
        Collections.addAll(list2, 1, 2, 3, 4, 5);
        Collections.reverse(list2);
        System.out.println("reverse  : " + list2);

        // frequency
        ArrayList<String> list3 = new ArrayList<>();
        Collections.addAll(list3, "a", "b", "a", "c", "a", "b");
        System.out.println("a 出现次数: " + Collections.frequency(list3, "a"));

        // replaceAll
        Collections.replaceAll(list3, "a", "Z");
        System.out.println("replaceAll: " + list3);
    }
}
