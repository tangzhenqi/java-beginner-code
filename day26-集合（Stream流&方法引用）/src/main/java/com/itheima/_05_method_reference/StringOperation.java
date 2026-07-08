package com.itheima._05_method_reference;

/**
 * 演示用的「他类」，提供静态方法和成员方法，
 * 供 {@link MRStaticDemo} / {@link MRInstanceObjectDemo} 引用。
 */
public class StringOperation {

    /** 静态方法：把 "123" 转成 123，供「类名::静态方法」引用。 */
    public static int parseInt(String s) {
        return Integer.parseInt(s);
    }

    /** 成员方法：转大写，供「对象::成员方法」引用。 */
    public String toUpper(String s) {
        return s.toUpperCase();
    }
}
