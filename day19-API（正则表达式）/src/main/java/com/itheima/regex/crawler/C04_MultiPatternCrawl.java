package com.itheima.regex.crawler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 知识点 11：用 | 拼出 “多套规则一起爬”
 * <p>
 * 场景：在同一段文本里同时抓 手机号 / 邮箱 / 座机 / 400 热线。
 * 关键点：用 | 把多套正则并联，必要时用 () 圈住各自的整体。
 * <p>
 * 注意 day19 原代码里把 | 错写到分组外侧，导致匹配错误。
 * 正确写法：(A)|(B)|(C)|(D) —— 整体是 “或”，每个候选独立。
 */
public class C04_MultiPatternCrawl {
    public static void main(String[] args) {
        String s = "来黑马程序员学习 Java，"
                + "手机号:18512516758，18512508907 "
                + "或者联系邮箱:boniu@itcast.cn，"
                + "座机电话:01036517895，010-98951256 "
                + "邮箱:bozai@itcast.cn，"
                + "热线电话:400-618-9090，400-618-4000，4006184000，4006189090";

        String phone = "1[3-9]\\d{9}";
        String email = "\\w+@[\\w&&[^_]]{2,6}(?:\\.[a-zA-Z]{2,3}){1,2}";
        String tel   = "0\\d{2,3}-?[1-9]\\d{4,9}";
        String hot   = "400-?[1-9]\\d{2}-?[1-9]\\d{3}";

        // 用 | 并联，整体加 () 不强制——这里为了可读性显式分组
        String regex = "(" + phone + ")|(" + email + ")|(" + tel + ")|(" + hot + ")";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);
        while (m.find()) {
            System.out.println(m.group());
        }
    }
}
