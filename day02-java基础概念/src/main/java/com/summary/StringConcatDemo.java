package com.summary;

/**
 * 知识点6（拓展）：字符串拼接 +
 *
 *   规则：+ 号左右任一为字符串时，结果是字符串。
 *   注意：从左往右依次计算。
 */
public class StringConcatDemo {
    public static void main(String[] args) {
        System.out.println(1 + 2 + "abc");    // 3abc    （先 1+2=3，再 3+"abc"）
        System.out.println("abc" + 1 + 2);    // abc12   （从左拼起后续都成字符串）
        System.out.println("结果是：" + (1 + 2));  // 结果是：3   括号提升优先级
    }
}
