package com.itheima.a09_extends_advanced;

import java.util.Objects;

/**
 * day13 之外的"延伸知识点"集中演示：
 *
 *  1. protected：父类的 protected 成员，对"同包"或"子类"可见，
 *     比 default（包私有）多了"跨包子类"也能访问的能力。
 *
 *  2. final 类：不能被继承。final 方法：不能被重写。
 *     常见用途：保护类的不变性（如 java.lang.String 就是 final 类）。
 *
 *  3. Object 是所有类的"隐式根父类"。没有 extends 时，编译器会替我们补上 extends Object。
 *     常用的 toString() / equals() 都来自 Object，按需重写即可。
 *
 *  4. equals() 重写：默认 Object 的 equals 是地址比较（==）。
 *     业务里常希望"属性都一致就认为相等"，所以需要重写。
 */
public class Demo {

    public static void main(String[] args) {
        // ---- 1. Object 的隐式继承 + toString / equals 重写 ----
        Point a = new Point(1, 2);
        Point b = new Point(1, 2);
        Point c = a;

        System.out.println("a = " + a);          // 自动调用 a.toString()
        System.out.println("a == b ? " + (a == b));         // 地址比较：false
        System.out.println("a.equals(b) ? " + a.equals(b)); // 重写后：true
        System.out.println("a == c ? " + (a == c));         // 同一个对象：true

        // ---- 2. final 演示 ----
        // class SubLocked extends LockedBox {} // 报错：LockedBox 是 final 类
        LockedBox box = new LockedBox();
        box.show(); // 父类的 final 方法，子类无法重写（即使有子类也不行）

        // ---- 3. protected 不在 main 中演示。
        //         规则：同包内可访问；跨包时只有"子类内部 通过子类引用"可访问。
    }
}

final class LockedBox {
    public final void show() {
        System.out.println("LockedBox: 我是 final 类的 final 方法");
    }
}

/**
 * 重写 toString / equals 的标准写法。
 * equals 要满足：自反、对称、传递、一致、与 null 比较返回 false。
 */
class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{x=" + x + ", y=" + y + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point other = (Point) o;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        // 重写 equals 通常要一起重写 hashCode，保证"相等对象 hash 也相等"
        return Objects.hash(x, y);
    }
}
