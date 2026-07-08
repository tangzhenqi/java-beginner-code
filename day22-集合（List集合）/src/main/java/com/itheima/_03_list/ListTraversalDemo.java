package com.itheima._03_list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 知识点十：List 的 5 种遍历方式总览（前 3 种是 Collection 通用的，后 2 种是 List 独有的）。
 *
 *  1. Iterator             —— 通用
 *  2. 增强 for             —— 通用
 *  3. Lambda forEach       —— 通用
 *  4. 普通 for + get/size  —— List 独有（依赖索引）
 *  5. ListIterator         —— List 独有，支持双向遍历，遍历中可 add/set/remove
 *
 *  实战选择：
 *   - 只读遍历：增强 for / forEach 最简洁
 *   - 边遍历边删除单个元素：Iterator.remove() 或 List.removeIf()
 *   - 边遍历边添加：必须用 ListIterator
 *   - 需要索引：普通 for
 */
public class ListTraversalDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        System.out.println("--- 1.Iterator ---");
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        System.out.println("--- 2.增强 for ---");
        for (String s : list) {
            System.out.println(s);
        }

        System.out.println("--- 3.Lambda forEach ---");
        list.forEach(System.out::println);

        System.out.println("--- 4.普通 for(带索引)---");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + " -> " + list.get(i));
        }

        // 5.ListIterator 在 ListIteratorDemo 里单独演示
    }
}
