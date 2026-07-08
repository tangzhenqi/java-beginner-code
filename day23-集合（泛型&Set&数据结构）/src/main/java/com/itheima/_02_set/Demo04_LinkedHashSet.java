package com.itheima._02_set;

import java.util.LinkedHashSet;

/*
 * 知识点 4：LinkedHashSet —— 有序、不重复、无索引
 *
 *  底层：HashSet 的子类 + 双向链表
 *      - 哈希表保证去重和 O(1) 查询
 *      - 额外的双向链表记录"插入顺序"，因此遍历时输出顺序 = 添加顺序
 *
 *  典型场景：
 *      既要去重，又要保持原始添加顺序的时候（比如保留输入顺序的"用户访问历史"）。
 */
public class Demo04_LinkedHashSet {
    public static void main(String[] args) {
        Student s1 = new Student("zhangsan", 23);
        Student s2 = new Student("lisi",     24);
        Student s3 = new Student("wangwu",   25);
        Student s4 = new Student("zhangsan", 23);

        LinkedHashSet<Student> set = new LinkedHashSet<>();
        set.add(s3);     // 第一个加入
        set.add(s2);
        set.add(s1);
        set.add(s4);     // 与 s1 相同，去重失败

        // 遍历顺序 = 添加顺序 [s3, s2, s1]
        set.forEach(System.out::println);
    }
}
