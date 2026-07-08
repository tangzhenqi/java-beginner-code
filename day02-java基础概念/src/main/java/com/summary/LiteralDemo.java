package com.summary;

/**
 * 知识点1：字面量（常量）
 *
 *   整数     666 , -777
 *   小数     1.93 , -3.71
 *   字符串   "黑马程序员"            双引号，可包含 0~多个字符
 *   字符     '男'                    单引号，必须且只能 1 个字符
 *   布尔     true , false
 *   空常量   null                    不能直接 println(null)，会编译报错
 */
public class LiteralDemo {
    public static void main(String[] args) {
        // 整数
        System.out.println(666);
        System.out.println(-777);

        // 小数
        System.out.println(1.93);
        System.out.println(-3.71);

        // 字符串
        System.out.println("黑马程序员");

        // 字符
        System.out.println('男');

        // 布尔
        System.out.println(true);
        System.out.println(false);

        // null 不能直接打印，用字符串形式打印
        System.out.println("null");
    }
}
