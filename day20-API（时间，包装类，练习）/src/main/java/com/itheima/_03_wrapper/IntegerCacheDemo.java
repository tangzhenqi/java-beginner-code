package com.itheima._03_wrapper;

/**
 * 知识点：Integer 的缓存池（IntegerCache）
 * <p>
 * 原理：
 *   Integer.valueOf(int) 内部对 -128 ~ 127 之间的数字做了缓存，
 *   多次取相同数字会返回 同一个对象（== 为 true）。
 *   超出范围会 new 新对象（== 为 false）。
 * <p>
 * new Integer(...) 不走缓存，每次都是新对象。
 * <p>
 * 拓展：Byte / Short / Long / Character 也有类似缓存机制。
 *       Boolean 只有 TRUE / FALSE 两个共享实例。
 */
public class IntegerCacheDemo {
    public static void main(String[] args) {
        // 在缓存范围内 → 同一对象
        Integer a = Integer.valueOf(127);
        Integer b = Integer.valueOf(127);
        System.out.println("127 == 127 ? " + (a == b)); // true

        // 超出缓存范围 → 不同对象
        Integer c = Integer.valueOf(128);
        Integer d = Integer.valueOf(128);
        System.out.println("128 == 128 ? " + (c == d)); // false

        // new 永远新对象
        Integer e = new Integer(127);
        Integer f = new Integer(127);
        System.out.println("new 127 == new 127 ? " + (e == f)); // false

        // 因此判断包装类相等，永远用 equals
        System.out.println("equals: " + c.equals(d)); // true
    }
}
