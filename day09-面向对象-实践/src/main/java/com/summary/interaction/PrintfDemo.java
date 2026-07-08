package com.summary.interaction;

/**
 * 拓展：printf 格式化输出
 *
 *   %s   字符串
 *   %d   整数
 *   %f   浮点数（%.2f 保留两位小数）
 *   %n   平台无关的换行
 *
 * 注意 System.out.printf 不会自动换行。
 */
public class PrintfDemo {
    public static void main(String[] args) {
        System.out.printf("你好啊 %s%n", "张三");
        System.out.printf("%s 向 %s 问好%n", "张三", "李四");
        System.out.printf("圆周率约为 %.2f%n", 3.1415926);
        System.out.printf("年龄：%d 岁%n", 18);

        // 也可以用 String.format 得到一个格式化后的字符串
        String s = String.format("[%s, %d, %.1f]", "abc", 10, 3.14);
        System.out.println(s);
    }
}
