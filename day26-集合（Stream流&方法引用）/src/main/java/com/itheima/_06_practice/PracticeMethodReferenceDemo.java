package com.itheima._06_practice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 练习 4：方法引用综合
 * <p>
 * 一段流水线集齐 5 种方法引用形式：
 * - 类名::静态方法     Integer::parseInt
 * - 类名::成员方法     String::trim
 * - 对象::成员方法     printer::println
 * - 类名::new          Actor::new (通过自定义构造，演示思路)
 * - 类型[]::new        Integer[]::new
 */
public class PracticeMethodReferenceDemo {
    public static void main(String[] args) {
        List<String> raw = Arrays.asList(" 10 ", " 20", "30 ", "40", "50");

        // String::trim   -> 去掉首尾空格      （类名::成员方法）
        // Integer::parseInt -> String 转 int  （类名::静态方法）
        Integer[] arr = raw.stream()
                .map(String::trim)
                .map(Integer::parseInt)
                .toArray(Integer[]::new); // 类型[]::new

        // 对象::成员方法
        java.io.PrintStream printer = System.out;
        Arrays.stream(arr).forEach(printer::println);

        // 构造方法引用：String -> Actor
        List<Actor> actors = Arrays.asList("张三,18", "李四,20").stream()
                .map(s -> {
                    String[] p = s.split(",");
                    return new Actor(p[0], Integer.parseInt(p[1]));
                })
                .collect(Collectors.toList());
        actors.forEach(System.out::println);
    }
}
