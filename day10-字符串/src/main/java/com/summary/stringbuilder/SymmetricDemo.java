package com.summary.stringbuilder;

import java.util.Scanner;

/**
 * 案例：判断对称字符串（回文串）。
 *
 * 思路 1：先 reverse 再 equals。
 * 思路 2：双指针对撞（拓展，性能更好，不开新字符串）。
 */
public class SymmetricDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个字符串：");
        String str = sc.next();

        // 思路 1：reverse + equals
        String reversed = new StringBuilder(str).reverse().toString();
        System.out.println(str.equals(reversed) ? "对称" : "不对称");

        // 思路 2：双指针对撞（拓展）
        System.out.println(isPalindrome(str) ? "对称(双指针)" : "不对称(双指针)");
    }

    public static boolean isPalindrome(String str) {
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
}
