package com.itheima.regex.validate;

/**
 * 知识点 6：身份证号码校验（简单版 + 严格版）
 * <p>
 * 演示要点：(?i) 内联开关（忽略大小写）、分组 ()、择一 |。
 * <pre>
 *   (?i)X  从这里开始 “忽略大小写”
 *   X((?i)Y)Z  只对分组 Y 忽略大小写
 * </pre>
 */
public class B03_IdCard {
    public static void main(String[] args) {

        // ---------- 1. 简单版：18 位，前 17 位数字，末位为数字或 X/x ----------
        String simple = "[1-9]\\d{16}[\\dXx]";
        System.out.println("41080119930228457x".matches(simple)); // true
        System.out.println("510801197609022309".matches(simple)); // true

        // ---------- 2. 简单版 + (?i) 忽略大小写写法 ----------
        // 末位 “数字或字母 x” → 使用 (?i) 让 x 同时匹配 X
        String simpleCI = "[1-9]\\d{16}(?:\\d|(?i)x)";
        System.out.println("15040119810705387X".matches(simpleCI)); // true

        // ---------- 3. 严格版：含 年 / 月 / 日 合法性 ----------
        //   前 6 位行政区划：[1-9]\d{5}
        //   年份：18 / 19 / 20 + 任意两位数字
        //   月份：01~09 / 10~12
        //   日期：01~09 / 10~29 / 30 / 31
        //   尾部：3 位数字 + 1 位（数字或 X/x）
        String strict = "[1-9]\\d{5}"
                + "(?:18|19|20)\\d{2}"
                + "(?:0[1-9]|1[0-2])"
                + "(?:0[1-9]|[12]\\d|3[01])"
                + "\\d{3}[\\dXx]";

        String[] cases = {
                "41080119930228457x",
                "510801197609022309",
                "15040119810705387X",
                "430102197606046442",
                "410801199313285671", // 13 月 → false
                "410801199302324570"  // 32 日 → false
        };
        for (String c : cases) {
            System.out.println(c + " : " + c.matches(strict));
        }

        // ---------- 4. 拓展：(?i) 只作用于局部 ----------
        // 只在分组 ((?i)b) 里忽略大小写，其余必须严格匹配
        String partialCI = "a((?i)b)c";
        System.out.println("aBc".matches(partialCI)); // true
        System.out.println("ABC".matches(partialCI)); // false
    }
}
