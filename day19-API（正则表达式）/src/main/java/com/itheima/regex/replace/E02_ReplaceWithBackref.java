package com.itheima.regex.replace;

/**
 * 知识点 17：替换串里使用 $n / ${name} 反向引用
 * <p>
 * 与 “正则内部用 \\n” 不同，在 replaceAll 第二个参数里要用 $n 表示 “第 n 组的内容”。
 */
public class E02_ReplaceWithBackref {
    public static void main(String[] args) {

        // ---------- 1. 合并重复字符 ----------
        // 把 “学学 / 编编编编 / 程程程程程程” 这种连续重复压缩成单个字符
        // (.)\\1+   把首字符当第 1 组，后面要求它至少再出现 1 次
        // $1        用 “第 1 组的内容” 替换匹配到的整段
        String str = "我要学学编编编编程程程程程程";
        System.out.println(str.replaceAll("(.)\\1+", "$1")); // 我要学编程

        // ---------- 2. 互换捕获组 ----------
        // 把 "姓-名" 互换为 "名 姓"
        String name = "张-三, 李-四, 王-五";
        System.out.println(name.replaceAll("(\\S)-(\\S)", "$2 $1"));

        // ---------- 3. 日期格式转换：yyyy-MM-dd → dd/MM/yyyy ----------
        String date = "今天 2026-05-25，明天 2026-05-26";
        System.out.println(date.replaceAll("(\\d{4})-(\\d{2})-(\\d{2})", "$3/$2/$1"));

        // ---------- 4. 给数字加千位分隔符（拓展：先行 + 后行断言）----------
        // (?<=\\d)(?=(\\d{3})+$)  在当前位置：左边是数字、右边是 3*n 个数字结尾
        // 注意：这个写法只适合 “纯整数字符串”
        String number = "1234567890";
        System.out.println(number.replaceAll("(?<=\\d)(?=(\\d{3})+$)", ",")); // 1,234,567,890
    }
}
