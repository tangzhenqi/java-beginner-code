package com.summary.traversal;

/**
 * 字符串反转的"朴素写法"：从尾到头遍历，用 + 累加。
 *
 * 注意：这种写法在每次 + 时都会"产生一个新的 String 对象"，性能很差。
 *       字符串长一点就应该用 StringBuilder.reverse() —— 见 stringbuilder 包。
 */
public class ReverseDemo {
    public static void main(String[] args) {
        System.out.println(reverse("abcdef"));   // fedcba
    }

    public static String reverse(String str) {
        String result = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            result += str.charAt(i);
        }
        return result;
    }
}
