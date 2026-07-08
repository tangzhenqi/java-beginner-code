package com.summary.extract;

/**
 * 案例：身份证号解析
 *
 *   身份证号 18 位：
 *     [0,6)    地址码
 *     [6,14)   出生日期：年(4) + 月(2) + 日(2)
 *     [14,17)  顺序码（其中 17 位用来判断性别）
 *     [17]     校验码
 *
 *   性别：第 17 位（索引 16）—— 奇男偶女。
 *   注意 charAt 返回的是 char，要转 int 可以 (c - '0') 或 Character.getNumericValue(c)。
 */
public class IdCardDemo {
    public static void main(String[] args) {
        String id = "321281202001011234";

        String year  = id.substring(6, 10);
        String month = id.substring(10, 12);
        String day   = id.substring(12, 14);
        char genderChar = id.charAt(16);
        int genderNum   = genderChar - '0';    // 经典的 char -> int 写法

        System.out.println("出生日期：" + year + "-" + month + "-" + day);
        System.out.println("性别：" + (genderNum % 2 == 0 ? "女" : "男"));
    }
}
