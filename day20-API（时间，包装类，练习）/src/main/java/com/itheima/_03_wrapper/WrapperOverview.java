package com.itheima._03_wrapper;

/**
 * 拓展：Java 的 8 种包装类总览
 * <p>
 * 基本类型     包装类       parseXxx     缓存范围
 * byte        Byte        parseByte    -128 ~ 127  全部
 * short       Short       parseShort   -128 ~ 127
 * int         Integer     parseInt     -128 ~ 127（默认，可通过 -XX:AutoBoxCacheMax 扩大上界）
 * long        Long        parseLong    -128 ~ 127
 * float       Float       parseFloat   —
 * double      Double      parseDouble  —
 * char        Character   （没有 parseChar）  0 ~ 127
 * boolean     Boolean     parseBoolean  Boolean.TRUE / Boolean.FALSE 两个共享实例
 * <p>
 * 共同特点：
 * - 都是 final 类，不可被继承。
 * - 都是不可变对象（值一旦确定不可修改）。
 * - 都重写了 equals 和 hashCode。
 * <p>
 * 共同的常用方法：MAX_VALUE / MIN_VALUE / valueOf / xxxValue / compare / parseXxx 等。
 */
public class WrapperOverview {
    public static void main(String[] args) {
        System.out.println("Byte    范围：" + Byte.MIN_VALUE + " ~ " + Byte.MAX_VALUE);
        System.out.println("Short   范围：" + Short.MIN_VALUE + " ~ " + Short.MAX_VALUE);
        System.out.println("Integer 范围：" + Integer.MIN_VALUE + " ~ " + Integer.MAX_VALUE);
        System.out.println("Long    范围：" + Long.MIN_VALUE + " ~ " + Long.MAX_VALUE);
        System.out.println("Float   范围：" + Float.MIN_VALUE + " ~ " + Float.MAX_VALUE);
        System.out.println("Double  范围：" + Double.MIN_VALUE + " ~ " + Double.MAX_VALUE);
        System.out.println("Character 范围：" + (int) Character.MIN_VALUE + " ~ " + (int) Character.MAX_VALUE);

        // Boolean 共享实例
        System.out.println(Boolean.TRUE == Boolean.valueOf("true"));  // true

        // 数值类包装类继承自 Number，提供 xxxValue 互转
        Integer i = 100;
        long  l = i.longValue();
        double d = i.doubleValue();
        System.out.println(l + " / " + d);

        // 比较两个数值大小的标准做法
        System.out.println(Integer.compare(1, 2)); // -1
    }
}
