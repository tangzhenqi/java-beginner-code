package com.itheima._02_set;

import java.util.TreeSet;

/*
 * 知识点 5：TreeSet —— 可排序、不重复、无索引
 *
 *  底层：红黑树（一种自平衡的二叉搜索树，O(log n) 增删查）。
 *
 *  排序两种方式：
 *      ① 自然排序：元素实现 Comparable，重写 compareTo()
 *      ② 比较器排序：创建 TreeSet 时传入 Comparator
 *
 *  优先级：
 *      如果两种都提供了，"比较器"优先。
 *
 *  去重规则：
 *      compareTo / compare 返回 0 视为同一元素，丢弃。
 *      —— 与 HashSet 不依赖 equals 不同，TreeSet 完全靠比较结果。
 */
public class Demo05_TreeSetNatural {
    public static void main(String[] args) {
        // 基本类型自然排序
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(4); ts.add(5); ts.add(1); ts.add(3); ts.add(2);
        System.out.println(ts);   // [1, 2, 3, 4, 5]

        // 自定义对象自然排序（Student 实现了 Comparable）
        TreeSet<Student> stuSet = new TreeSet<>();
        stuSet.add(new Student("zhangsan", 23));
        stuSet.add(new Student("lisi",     21));
        stuSet.add(new Student("wangwu",   25));
        stuSet.add(new Student("zhaoliu",  23));   // 同 23 岁，按姓名兜底
        stuSet.add(new Student("zhangsan", 23));   // 与第一个完全相同，去重
        stuSet.forEach(System.out::println);
    }
}
