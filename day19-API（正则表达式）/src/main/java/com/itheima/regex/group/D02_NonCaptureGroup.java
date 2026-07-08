package com.itheima.regex.group;

/**
 * 知识点 14：非捕获分组 (?:X)
 * <p>
 * 当我们只是想 “把一段表达式括起来” 让数量词 / 择一作用于它，但 并不需要 后续再引用本组数据时，
 * 用 (?:X) 比 (X) 更清晰，而且不占用组号。
 * <p>
 * 同属 “非捕获” 家族的还有：(?=X) 正向先行、(?!X) 反向先行（见 C03_Lookaround）。
 */
public class D02_NonCaptureGroup {
    public static void main(String[] args) {

        // ---------- 1. 捕获 vs 非捕获 ----------
        // 这俩 matches 行为完全一样；区别只在 “是否分配组号”
        String captured    = "(\\d{4})-(\\d{2})-(\\d{2})";  // 组号 1,2,3
        String nonCaptured = "(?:\\d{4})-(?:\\d{2})-(?:\\d{2})"; // 无组号
        System.out.println("2026-05-25".matches(captured));    // true
        System.out.println("2026-05-25".matches(nonCaptured)); // true

        // ---------- 2. 让数量词作用于一段子表达式，但不想被捕获 ----------
        // 邮箱顶级域，可能是 .com 也可能是 .com.cn —— 用 (?:...) 整体重复 1~2 次
        String email = "\\w+@[\\w&&[^_]]{2,6}(?:\\.[a-zA-Z]{2,3}){1,2}";
        System.out.println("a@qq.com".matches(email));        // true
        System.out.println("a@xx.com.cn".matches(email));     // true

        // ---------- 3. 注意：非捕获组里不能被 \\n 反向引用 ----------
        // 下面这条会抛 PatternSyntaxException —— (?: ) 不分配组号，\\1 找不到
        // String bad = "[1-9]\\d{16}(?:\\d|X|x)\\1";
        // "41080119930228457x".matches(bad);
    }
}
