package com.itheima.regex.basics;

/**
 * 知识点 1：字符类（Character Class）
 * <p>
 * 字符类用 [] 包裹，表示 “匹配方括号内任意一个字符”。注意：一个 [] 只匹配一个字符。
 * <ul>
 *     <li>[abc]        : 只能是 a、b 或 c</li>
 *     <li>[^abc]       : 除 a、b、c 之外的任意一个字符（^ 写在 [] 内表示 “非”）</li>
 *     <li>[a-zA-Z]     : 范围，a~z 或 A~Z 的任意一个</li>
 *     <li>[a-d[m-p]]   : 并集，a~d 或 m~p</li>
 *     <li>[a-z&&[def]] : 交集，a~z 与 d/e/f 的交集 → d、e、f</li>
 *     <li>[a-z&&[^bc]] : a~z 中除去 b、c</li>
 * </ul>
 * 拓展：String.matches(regex) 要求 整串完全匹配，等价于 ^regex$。
 */
public class A01_CharacterClass {
    public static void main(String[] args) {
        // ---------- 1. 枚举 ----------
        System.out.println("a".matches("[abc]"));   // true
        System.out.println("z".matches("[abc]"));   // false

        // ---------- 2. 取反 ----------
        System.out.println("a".matches("[^abc]"));  // false
        System.out.println("z".matches("[^abc]"));  // true
        // matches 要求整串匹配。一个 [] 只能匹配 1 个字符，所以 "zz" 直接为 false
        System.out.println("zz".matches("[^abc]"));         // false
        System.out.println("zz".matches("[^abc][^abc]"));   // true

        // ---------- 3. 范围 ----------
        System.out.println("a".matches("[a-zA-Z]"));  // true
        System.out.println("0".matches("[a-zA-Z0-9]"));// true

        // ---------- 4. 并集 ----------
        System.out.println("a".matches("[a-d[m-p]]")); // true
        System.out.println("e".matches("[a-d[m-p]]")); // false

        // ---------- 5. 交集 ----------
        System.out.println("d".matches("[a-z&&[def]]")); // true
        System.out.println("a".matches("[a-z&&[def]]")); // false

        // ---------- 6. 差集（交 + 取反）----------
        System.out.println("a".matches("[a-z&&[^bc]]")); // true
        System.out.println("b".matches("[a-z&&[^bc]]")); // false

        // ---------- 7. 拓展：常见 “符号字符” 的字符类 ----------
        // 仅允许 字母 / 数字 / 下划线 中除 _ 之外
        System.out.println("A".matches("[\\w&&[^_]]")); // true
        System.out.println("_".matches("[\\w&&[^_]]")); // false
    }
}
