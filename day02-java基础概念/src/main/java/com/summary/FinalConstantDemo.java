package com.summary;

/**
 * 知识点7（拓展）：final 常量
 *
 *   格式：  final 数据类型 常量名 = 值;
 *   特点：  一旦赋值，不可再修改
 *   规范：  常量名通常全大写，多个单词用 _ 分隔
 */
public class FinalConstantDemo {
    public static void main(String[] args) {
        final double PI = 3.1415926;
        final int MAX_USER = 100;

        // PI = 3.14;        // 编译报错：不能再赋值
        // MAX_USER = 200;   // 编译报错

        System.out.println("PI = " + PI);
        System.out.println("最大用户数 = " + MAX_USER);
    }
}
