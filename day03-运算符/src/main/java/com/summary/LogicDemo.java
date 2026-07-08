package com.summary;

/**
 * 知识点6：逻辑运算符
 *
 *   普通逻辑：
 *     &     与    两边都为真才为真
 *     |     或    两边都为假才为假
 *     ^     异或  相同为 false，不同为 true
 *     !     非    取反（不要连写多次）
 *
 *   短路逻辑（推荐使用）：
 *     &&    短路与   左边为 false，右边不再执行
 *     ||    短路或   左边为 true，右边不再执行
 */
public class LogicDemo {
    public static void main(String[] args) {
        // & |
        System.out.println(true & false);   // false
        System.out.println(true | false);   // true

        // ^ !
        System.out.println(true ^ false);   // true
        System.out.println(!false);         // true

        // 短路演示：& 没有短路效果
        int a = 10, b = 10;
        boolean r1 = (++a < 5) & (++b < 5);
        System.out.println(r1 + "  a=" + a + " b=" + b);   // false  a=11 b=11

        // 短路演示：&& 左边 false，右边不执行
        int x = 10, y = 10;
        boolean r2 = (++x < 5) && (++y < 5);
        System.out.println(r2 + "  x=" + x + " y=" + y);   // false  x=11 y=10
    }
}
