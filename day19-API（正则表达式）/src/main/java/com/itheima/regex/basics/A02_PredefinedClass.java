package com.itheima.regex.basics;

/**
 * 知识点 2：预定义字符与转义
 * <p>
 * 一些常用字符类有 “速记符号”，Java 字符串里需要写两个反斜杠，因为 \ 本身在 Java 字符串里是转义符。
 * <ul>
 *     <li>.    : 任意一个字符（除换行符外，默认情况下）</li>
 *     <li>\\d  : 数字 [0-9]</li>
 *     <li>\\D  : 非数字</li>
 *     <li>\\w  : 单词字符 [a-zA-Z_0-9]</li>
 *     <li>\\W  : 非单词字符</li>
 *     <li>\\s  : 空白字符（空格、\t、\n、\r、\f）</li>
 *     <li>\\S  : 非空白字符</li>
 *     <li>\\b  : 单词边界（零宽，常用于精确匹配单词）</li>
 * </ul>
 * 关于转义的两层含义：
 * <pre>
 *     "\""   先被 Java 字符串解析为一个 "
 *     "\\d"  先被 Java 字符串解析为 \d，再交给正则引擎理解为 “数字”
 * </pre>
 */
public class A02_PredefinedClass {
    public static void main(String[] args) {
        // ---------- 1. 字符串转义 ----------
        System.out.println("\"");                       // 打印一个双引号
        System.out.println("C:\\Users\\demo\\a.txt");   // 打印 Windows 风格路径

        // ---------- 2. . 任意一个字符 ----------
        System.out.println("你".matches("."));   // true
        System.out.println("你a".matches("..")); // true

        // ---------- 3. \d 数字 ----------
        System.out.println("3".matches("\\d"));   // true
        System.out.println("a".matches("\\d"));   // false
        System.out.println("333".matches("\\d")); // false（只能匹配 1 个字符）

        // ---------- 4. \w 单词字符 ----------
        System.out.println("z".matches("\\w"));  // true
        System.out.println("_".matches("\\w"));  // true
        System.out.println("你".matches("\\w")); // false
        System.out.println("你".matches("\\W")); // true

        // ---------- 5. 拓展：\s 与 \b ----------
        System.out.println("  ".matches("\\s+"));      // true
        System.out.println("hello world".split("\\s+").length); // 2

        // \b 单词边界：用于在大串中找完整单词（需要配合 Pattern/Matcher 才能体现，这里仅演示语义）
        System.out.println("Java".matches("\\bJava\\b")); // true
    }
}
