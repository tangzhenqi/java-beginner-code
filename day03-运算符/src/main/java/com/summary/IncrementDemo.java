package com.summary;

/**
 * 知识点2：自增 ++ 与 自减 --
 *
 *   单独使用：a++ 与 ++a 效果一致，都是 +1
 *   参与运算：
 *     a++   先用后加：先把原值取出参与运算，再自增
 *     ++a   先加后用：先自增，再把新值参与运算
 *
 * 注意：开发中不建议在一行写多次 ++/--，可读性差。
 */
public class IncrementDemo {
    public static void main(String[] args) {
        // 单独使用
        int a = 10;
        a++;
        System.out.println(a);   // 11
        ++a;
        System.out.println(a);   // 12
        a--;
        System.out.println(a);   // 11

        // 参与运算
        int x = 10;
        int y = x++;             // y=10, x=11
        int z = ++x;             // x=12, z=12
        System.out.println("x=" + x + " y=" + y + " z=" + z);
    }
}
