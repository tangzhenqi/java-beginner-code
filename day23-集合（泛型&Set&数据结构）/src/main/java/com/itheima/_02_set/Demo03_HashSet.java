package com.itheima._02_set;

import java.util.HashSet;

/*
 * 知识点 3：HashSet —— 无序、不重复、无索引
 *
 *  去重原理：调用 add(e) 时
 *      step1 : 算出 e 的 hashCode，定位到数组中的某个桶
 *      step2 : 若桶为空 → 直接放入
 *      step3 : 若桶非空 → 依次 equals 比较桶里已有元素
 *                  - 全部不相等：链入链表 / 红黑树
 *                  - 有一个相等：丢弃 e，不重复
 *
 *  所以自定义类放进 HashSet，必须同时重写 hashCode 和 equals。
 *
 *  底层结构（JDK 8+）：
 *      数组 + 链表 + 红黑树
 *      - 默认初始容量 16，加载因子 0.75 → 元素超过 12 触发扩容
 *      - 链表长度 >= 8 且数组长度 >= 64 时，链表转为红黑树（提升查找效率）
 *      - 树节点数 <= 6 时回退为链表
 */
public class Demo03_HashSet {
    public static void main(String[] args) {
        Student s1 = new Student("zhangsan", 23);
        Student s2 = new Student("lisi",     24);
        Student s3 = new Student("wangwu",   25);
        Student s4 = new Student("zhangsan", 23);  // 与 s1 内容相同

        HashSet<Student> set = new HashSet<>();
        System.out.println(set.add(s1));   // true
        System.out.println(set.add(s2));   // true
        System.out.println(set.add(s3));   // true
        System.out.println(set.add(s4));   // false：被去重

        System.out.println("集合 size = " + set.size());
        System.out.println(set);
    }
}
