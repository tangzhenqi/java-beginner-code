package com.itheima.a05_extends_members;

/**
 * 继承中"成员变量"的访问特点（就近原则） + this/super 区别。
 *
 * 查找顺序：
 *   局部变量 -> 本类成员变量 -> 父类成员变量 -> 父类的父类 ...
 *
 * 关键字：
 *   this.xxx   : 从本类成员开始向上查找（跳过局部变量）
 *   super.xxx  : 从父类成员开始向上查找（跳过本类）
 *
 * 注意：成员变量不存在"重写"概念，只有覆盖（看声明类型 / 看 this/super 指向）。
 */
public class Demo {
    public static void main(String[] args) {
        new Zi().show();
    }
}

class Ye {
    String name = "Ye";
}

class Fu extends Ye {
    String name = "Fu";
}

class Zi extends Fu {
    String name = "Zi";

    public void show() {
        String name = "局部变量";

        System.out.println(name);        // 就近原则 -> "局部变量"
        System.out.println(this.name);   // 本类成员 -> "Zi"
        System.out.println(super.name);  // 父类成员 -> "Fu"
        // 没有 super.super.name 语法；要访问 Ye，需要 Fu 显式提供方法去暴露
    }
}
