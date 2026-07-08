package com.itheima.regex.group;

/**
 * 知识点 13：捕获分组（Capturing Group）与反向引用（Backreference）
 * <p>
 * 用 () 把一段子表达式 “捕获” 起来，按从左到右出现顺序得到组号 1、2、3...（0 表示整体）。
 * 在 同一个正则 中再次引用某一组用 \\n（n 是组号）；
 * 在 替换字符串 中再次引用某一组用 $n。
 * <p>
 * 经典例子：判断 “首尾一致”。
 */
public class D01_CaptureGroupAndBackref {
    public static void main(String[] args) {

        // ---------- 1. 首尾是同一个字符 ----------
        // (.) 把首字符捕获为第 1 组；.+ 匹配中间；\\1 表示 “再次出现第 1 组的内容”
        String r1 = "(.).+\\1";
        System.out.println("a123a".matches(r1));   // true
        System.out.println("b456b".matches(r1));   // true
        System.out.println("a123b".matches(r1));   // false

        // ---------- 2. 首尾是同一段字符串 ----------
        String r2 = "(.+).+\\1";
        System.out.println("abc123abc".matches(r2));   // true
        System.out.println("&!@abc&!@".matches(r2));   // true
        System.out.println("abc123abd".matches(r2));   // false

        // ---------- 3. 首尾是 “内部完全相同” 的若干字符 ----------
        //   (.)\\1* 把 “首字符 + 它的若干次重复” 整体作为第 1 组
        //   组内部的 \\2 是 “第 2 组” —— 就是首字符那一对小括号
        //   末尾 \\1 复用整段
        String r3 = "((.)\\2*).+\\1";
        System.out.println("aaa123aaa".matches(r3));   // true
        System.out.println("bbb456bbb".matches(r3));   // true
        System.out.println("&&abc&&".matches(r3));     // true
        System.out.println("aaa123aab".matches(r3));   // false
    }
}
