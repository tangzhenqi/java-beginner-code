package com.itheima.day14.finalkw;

/**
 * final 修饰类 —— 不能被继承。
 *
 * 典型的 final 类：
 *     - java.lang.String
 *     - java.lang.Math
 *     - 包装类（Integer、Double 等）
 * 这些类设计成"不可继承"，以保证其行为/不变性不被破坏。
 */
public final class SealedBox {
    public void open() {
        System.out.println("打开一个最终类（final class）的盒子");
    }
}

// 编译失败：不能继承 final 类
// class SubBox extends SealedBox {}
