package com.itheima._05_generics;

/**
 * 三层继承体系，用来配合演示"泛型无继承性"和"通配符"。
 *
 *      Ye  (爷)
 *       ^
 *      Fu  (父)
 *       ^
 *      Zi  (子)
 */
public class _10_Family {
    public static class Ye {}
    public static class Fu extends Ye {}
    public static class Zi extends Fu {}
    public static class Other {} // 与 Ye/Fu/Zi 无继承关系
}
