package com.itheima.regex.basics;

/**
 * 知识点 3：数量词（Quantifier）与边界
 * <p>
 * 数量词写在某个 “单元” 之后，控制这个单元出现的次数。
 * 一个 “单元” 可以是：一个字符、一个字符类 []、一个分组 ()。
 * <ul>
 *     <li>X?       : 0 次或 1 次</li>
 *     <li>X*       : 0 次或多次</li>
 *     <li>X+       : 1 次或多次</li>
 *     <li>X{n}     : 恰好 n 次</li>
 *     <li>X{n,}    : 至少 n 次</li>
 *     <li>X{n,m}   : n 到 m 次（含两端）</li>
 * </ul>
 * 边界（用于 Pattern/Matcher 找子串时定位）：
 * <ul>
 *     <li>^   行首</li>
 *     <li>$   行尾</li>
 *     <li>\\b 单词边界</li>
 * </ul>
 * 注意：String.matches 默认要求整串匹配，所以 ^ $ 通常可省略。
 */
public class A03_Quantifier {
    public static void main(String[] args) {
        // ---------- 1. + 至少 1 次 ----------
        System.out.println("a".matches("a+"));     // true
        System.out.println("aaaa".matches("a+"));  // true
        System.out.println("".matches("a+"));      // false

        // ---------- 2. * 0 次或多次 ----------
        System.out.println("".matches("a*"));      // true
        System.out.println("aaa".matches("a*"));   // true

        // ---------- 3. ? 0 次或 1 次 ----------
        System.out.println("color".matches("colou?r"));  // true
        System.out.println("colour".matches("colou?r")); // true

        // ---------- 4. 精确次数 ----------
        // 至少 6 位的数字 / 字母 / 下划线
        System.out.println("abc123_".matches("\\w{6,}"));   // true
        System.out.println("ab12".matches("\\w{6,}"));      // false

        // 4 位字符且不含下划线
        System.out.println("23dF".matches("[\\w&&[^_]]{4}")); // true
        System.out.println("23_F".matches("[\\w&&[^_]]{4}")); // false

        // ---------- 5. 数量词作用范围：分组 ----------
        // (ab){3} 表示 "ab" 重复 3 次
        System.out.println("ababab".matches("(ab){3}")); // true
        System.out.println("abab".matches("(ab){3}"));   // false

        // ---------- 6. 拓展：与字符类的组合 ----------
        // 必须是数字开头，后跟 5~19 位数字 → QQ 校验
        System.out.println("1234567890".matches("[1-9]\\d{5,19}")); // true
        System.out.println("0234567".matches("[1-9]\\d{5,19}"));    // false
    }
}
