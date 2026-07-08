package com.itheima._03_wrapper;

/**
 * 知识点：Integer 对象的获取方式
 * <p>
 * - JDK5 前：使用构造方法
 *   Integer(int)   Integer(String)
 *   （JDK9 起被标记为废弃）
 * - JDK5 后：推荐使用静态方法 valueOf
 *   Integer.valueOf(int) / valueOf(String) / valueOf(String, int radix)
 */
public class IntegerCreateDemo {
    public static void main(String[] args) {
        // 1. 构造方法（不推荐）
        Integer i1 = new Integer(1);
        Integer i2 = new Integer("1");
        System.out.println(i1 + " / " + i2);

        // 2. 静态方法（推荐）
        // 为什么推荐用 valueOf 而不是 new Integer()：
        //   ① 缓存复用：valueOf 内部维护了 -128~127 的整数缓存池（IntegerCache），
        //      该范围内会返回同一个缓存对象，不会重复创建，节省内存。
        //      例：Integer.valueOf(127) == Integer.valueOf(127) 为 true；
        //          new Integer(127) == new Integer(127) 为 false（每次都新建对象）。
        //      注意：超过 127 不走缓存，判断值相等永远用 equals()，不要用 ==。
        //   ② 官方废弃：new Integer() 自 JDK9 起被标记为 @Deprecated，
        //      官方推荐用 valueOf，空间和时间性能都更好。
        //   ③ 自动装箱底层用的就是 valueOf：Integer i = 123; 等价于 Integer.valueOf(123)。
        //   ④ 只有静态方法能按指定进制解析字符串（构造方法做不到）。
        Integer i3 = Integer.valueOf(123);
        Integer i4 = Integer.valueOf("123");
        Integer i5 = Integer.valueOf("123", 8); // 把 "123" 当作八进制解析 → 83

        System.out.println(i3);
        System.out.println(i4);
        System.out.println(i5);
    }
}
