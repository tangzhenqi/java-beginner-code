package com.summary.traversal;

/**
 * 知识点 3：字符串的遍历
 *
 *   str.length()    长度
 *   str.charAt(i)   返回索引 i 处的字符
 *
 *   for (int i = 0; i < str.length(); i++) {
 *       char c = str.charAt(i);
 *       ...
 *   }
 *
 * 拓展：还可以 str.toCharArray() 拿到 char[]，再用 foreach 遍历：
 *   for (char c : str.toCharArray()) { ... }
 */
public class StringTraversalDemo {
    public static void main(String[] args) {
        String str = "Hello123";

        // 经典写法
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            System.out.println(i + " -> " + c);
        }

        // 增强 for（拓展）
        for (char c : str.toCharArray()) {
            System.out.print(c + " ");
        }
        System.out.println();
    }
}
