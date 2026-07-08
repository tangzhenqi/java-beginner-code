package com.itheima._04_treemap;

import java.util.TreeMap;

/**
 * TreeMap + 自定义对象作为键：通过 Comparable 指定默认排序规则。
 *
 * 需求：键=Student，值=籍贯
 *   按年龄升序排，年龄相同按姓名字母排，同姓名年龄视为同一人。
 *
 * 两种指定规则的方式（任选其一）：
 *   1. JavaBean 实现 Comparable（见 Student）
 *   2. 构造 TreeMap 时传入 Comparator（见 TreeMapComparatorDemo）
 *
 * 优先级：构造器的 Comparator > JavaBean 的 Comparable。
 */
public class TreeMapComparableDemo {
    public static void main(String[] args) {
        TreeMap<Student, String> tm = new TreeMap<>();
        tm.put(new Student("zhangsan", 23), "江苏");
        tm.put(new Student("lisi",     24), "天津");
        tm.put(new Student("wangwu",   25), "北京");
        // 同姓名同年龄 -> 覆盖前一个值
        tm.put(new Student("zhangsan", 23), "上海");

        System.out.println("按年龄升序：");
        tm.forEach((stu, addr) -> System.out.println(stu + " = " + addr));
    }
}
