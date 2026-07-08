package com.itheima.a05objects;

import java.util.Objects;

/**
 * 知识点五：Objects 工具类（java.util.Objects）
 * <p>
 * 区别于 java.lang.Object：
 *   Object  —— 所有类的父类（实例方法）
 *   Objects —— 提供"null 安全"操作的工具类（全是 static 方法）
 * <p>
 * 核心 API：
 *   Objects.equals(a, b)        null 安全的相等比较：先判 a 是否为 null，再走 a.equals(b)
 *   Objects.isNull(obj)         obj == null
 *   Objects.nonNull(obj)        obj != null
 */
public class ObjectsSummary {
    public static void main(String[] args) {
        // 1. equals 的 null 安全：手写要先判空，Objects 帮我们做了
        String a = null;
        String b = "hello";

        // 传统写法：
        // boolean r = (a == null ? b == null : a.equals(b));
        // 用 Objects：
        boolean r = Objects.equals(a, b);
        System.out.println(r);                          // false

        // 即使两个都 null，也认为相等
        System.out.println(Objects.equals(null, null)); // true

        // 2. isNull / nonNull —— 主要为 Stream / 函数式风格服务（方法引用）
        System.out.println(Objects.isNull(null));       // true
        System.out.println(Objects.nonNull("x"));       // true
    }
}
