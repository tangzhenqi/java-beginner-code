package com.summary.extract;

/**
 * 知识点 4：字符串截取
 *
 *   str.substring(beginIndex)            从 beginIndex 截到末尾
 *   str.substring(beginIndex, endIndex)  截取 [beginIndex, endIndex)，含头不含尾
 *
 * 经典场景：手机号脱敏。
 */
public class SubstringDemo {
    public static void main(String[] args) {
        String phone = "13112349468";

        String start = phone.substring(0, 3);   // "131"
        String end   = phone.substring(7);      // "9468"
        String mask  = start + "****" + end;    // 131****9468

        System.out.println(mask);
    }
}
