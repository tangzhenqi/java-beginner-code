package com.itheima._04_extend;

import java.time.LocalDate;

/**
 * 拓展：JDK8 新时间类的"不可变性"
 * <p>
 * LocalDate、LocalTime、LocalDateTime、Instant、ZonedDateTime 都是不可变对象。
 * 调用 plus/minus/with 这些方法 不会修改原对象，而是返回一个新对象。
 * <p>
 * 这是新 API 相对 Date 的最大改进之一：可以放心地在多线程间共享。
 */
public class ImmutableDemo {
    public static void main(String[] args) {
        LocalDate origin = LocalDate.of(2024, 1, 1);

        // 看似"加一天"，实则返回了新对象
        LocalDate tomorrow = origin.plusDays(1);

        System.out.println("origin    : " + origin);   // 仍然是 2024-01-01
        System.out.println("tomorrow  : " + tomorrow); // 2024-01-02
        System.out.println("== ? " + (origin == tomorrow));         // false
        System.out.println("equals ? " + origin.equals(tomorrow));  // false

        // 错误写法：以为修改了 origin，实际丢弃了返回值
        origin.plusDays(1);
        System.out.println("错误写法后 origin: " + origin); // 还是 2024-01-01

        // 正确写法：用返回值覆盖
        origin = origin.plusDays(1);
        System.out.println("覆盖后 origin: " + origin);  // 2024-01-02
    }
}
