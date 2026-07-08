package com.summary;

/**
 * 知识点5：关系（比较）运算符
 *
 *   ==   等于
 *   !=   不等于
 *   >    大于
 *   <    小于
 *   >=   大于等于
 *   <=   小于等于
 *
 *   结果一定是 boolean。
 *
 *   注意：基本类型 == 比较的是值；引用类型 == 比较的是地址（见后续 String 章节）。
 */
public class CompareDemo {
    public static void main(String[] args) {
        int a = 10;
        int b = 10;
        int c = 20;

        System.out.println(a == b);    // true
        System.out.println(a == c);    // false
        System.out.println(a != c);    // true
        System.out.println(a > c);     // false
        System.out.println(a <= b);    // true
    }
}
