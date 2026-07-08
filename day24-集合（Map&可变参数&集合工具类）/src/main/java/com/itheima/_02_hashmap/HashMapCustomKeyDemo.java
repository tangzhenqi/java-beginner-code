package com.itheima._02_hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * 需求：以 Student（姓名 + 年龄）为键，籍贯为值。
 * 同姓名同年龄认为是同一个学生。
 *
 * 核心点：
 *   - HashMap 判断键是否重复，先比 hashCode 再比 equals
 *   - 自定义类必须同时重写这两个方法
 *   - 在 Student.java 中已经重写
 */
public class HashMapCustomKeyDemo {
    public static void main(String[] args) {
        HashMap<Student, String> hm = new HashMap<>();
        hm.put(new Student("zhangsan", 23), "江苏");
        hm.put(new Student("lisi",     24), "浙江");
        hm.put(new Student("wangwu",   25), "福建");
        // 与上一行同姓名同年龄 -> 视为同一个键，会覆盖值
        hm.put(new Student("wangwu",   25), "山东");

        // 三种遍历
        System.out.println("------ keySet ------");
        for (Student key : hm.keySet()) {
            System.out.println(key + " = " + hm.get(key));
        }

        System.out.println("------ entrySet ------");
        for (Map.Entry<Student, String> entry : hm.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        System.out.println("------ forEach ------");
        hm.forEach((stu, addr) -> System.out.println(stu + " = " + addr));
    }
}
