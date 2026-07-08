package com.itheima.regex.validate;

/**
 * 知识点 4：QQ / 用户名校验
 * <p>
 * 思路总结（“数据驱动法”写正则）：
 *   1. 拿一条正确数据，按肉眼可见的规律，从左到右拆成几段。
 *   2. 每一段挑一个合适的字符类 + 数量词。
 *   3. 拼接起来，再用 matches 跑一遍正反例。
 */
public class B01_QQAndUsername {
    public static void main(String[] args) {
        // ---------- QQ 号 ----------
        // 规则：6 ~ 20 位，纯数字，不能以 0 开头
        String qqRegex = "[1-9]\\d{5,19}";
        System.out.println("1234567890".matches(qqRegex)); // true
        System.out.println("0234567".matches(qqRegex));    // false（0 开头）
        System.out.println("123".matches(qqRegex));        // false（位数不足）

        // ---------- 用户名 ----------
        // 规则：大小写字母 / 数字 / 下划线，共 4 ~ 16 位
        String userRegex = "\\w{4,16}";
        System.out.println("zhangsan".matches(userRegex)); // true
        System.out.println("li".matches(userRegex));       // false
        System.out.println("$user1".matches(userRegex));   // false（$ 不是 \w）

        // ---------- 拓展：纯字母开头的用户名 ----------
        // 规则：首字符必须是字母，其后可跟 字母/数字/下划线，总长 4~16
        String userRegex2 = "[a-zA-Z]\\w{3,15}";
        System.out.println("zhang3".matches(userRegex2));  // true
        System.out.println("3zhang".matches(userRegex2));  // false
    }
}
