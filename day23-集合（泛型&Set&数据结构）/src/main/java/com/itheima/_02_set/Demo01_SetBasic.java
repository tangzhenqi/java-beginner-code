package com.itheima._02_set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
 * 知识点 1：Set 系列集合的共性
 *
 *  Set 接口的特点（与 List 对比）：
 *      ┌────────────┬────────────────────────┬────────────────────────┐
 *      │   特性     │   List                 │   Set                  │
 *      ├────────────┼────────────────────────┼────────────────────────┤
 *      │ 有无索引   │ 有索引                 │ 无索引                 │
 *      │ 是否重复   │ 允许重复               │ 不允许重复             │
 *      │ 是否有序   │ 有序（按添加顺序）     │ 看具体实现             │
 *      │ 取出元素   │ get(i) / 迭代 / for    │ 只能迭代 / for / λ     │
 *      └────────────┴────────────────────────┴────────────────────────┘
 *
 *  Set 接口下三个常用实现：
 *      HashSet        ：无序、不重复、无索引       — 底层哈希表
 *      LinkedHashSet  ：有序（插入顺序）、不重复    — 哈希表 + 双向链表
 *      TreeSet        ：可排序、不重复、无索引      — 红黑树
 *
 *  add 方法的返回值很重要：
 *      true  → 集合发生改变（成功加入）
 *      false → 元素已存在（被去重）
 */
public class Demo01_SetBasic {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();

        System.out.println(set.add("张三"));   // true
        System.out.println(set.add("张三"));   // false，重复
        System.out.println(set.add("李四"));   // true
        System.out.println(set.add("王五"));   // true

        // 三种遍历方式（Set 没有索引，所以不能用普通 for）
        // 1) 迭代器
        System.out.println("---- 迭代器 ----");
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        // 2) 增强 for
        System.out.println("---- 增强 for ----");
        for (String s : set) {
            System.out.println(s);
        }

        // 3) Lambda（forEach）
        System.out.println("---- Lambda ----");
        set.forEach(s -> System.out.println(s));
    }
}
