package com.itheima._03_wrapper;

import java.util.ArrayList;

/**
 * 知识点：自动装箱、自动拆箱（JDK5 起）
 * <p>
 * - 自动装箱：基本类型 → 包装类型（底层调 Integer.valueOf(int)）
 * - 自动拆箱：包装类型 → 基本类型（底层调 intValue()）
 * <p>
 * 结论：JDK5 之后，int 和 Integer 在源码层面几乎可以互相替换。
 * <p>
 * 拓展坑点：
 * 1. Integer 为 null 时拆箱会抛 NullPointerException。
 * 2. 集合只能放对象，所以 List<Integer> 的 add(int) 实际上发生了自动装箱。
 */
public class AutoBoxUnboxDemo {
    public static void main(String[] args) {
        // 自动装箱
        Integer boxed = 10;             // 等价于 Integer.valueOf(10)
        // 自动拆箱
        int unboxed = boxed;            // 等价于 boxed.intValue()
        System.out.println(boxed + " / " + unboxed);

        // 集合中的自动装箱
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);                    // int → Integer
        int first = list.get(0);        // Integer → int
        System.out.println("从集合取出：" + first);

        // 坑点：null 自动拆箱
        try {
            Integer nullValue = null;
            int v = nullValue;          // 编译通过、运行报 NPE
            System.out.println(v);
        } catch (NullPointerException ex) {
            System.out.println("null 自动拆箱抛 NPE");
        }
    }
}
