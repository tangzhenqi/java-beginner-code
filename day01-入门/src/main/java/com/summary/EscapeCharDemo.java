package com.summary;

/**
 * 知识点5（拓展）：转义字符
 *
 *   \t   制表符（按列对齐，常用于表格输出）
 *   \n   换行
 *   \r   回车
 *   \\   反斜杠
 *   \"   双引号
 *   \'   单引号
 *
 * 提示：转义字符在字符串字面量中按 1 个字符算。
 */
public class EscapeCharDemo {
    public static void main(String[] args) {
        // 制表符对齐
        System.out.println("姓名\t年龄\t性别");
        System.out.println("张三\t20\t男");
        System.out.println("李四\t21\t女");

        // 换行
        System.out.println("第一行\n第二行");

        // 反斜杠 + 双引号
        System.out.println("路径：C:\\Users\\admin");
        System.out.println("他说：\"你好\"");
    }
}
