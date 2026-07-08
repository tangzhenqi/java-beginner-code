package com.itheima._01_jdk7_date;

import java.util.Date;
import java.util.Random;

/**
 * 知识点：比较两个 Date 的先后
 * <p>
 * 思路：两个时间的先后等价于其 getTime() 毫秒值大小比较。
 * <p>
 * 拓展：
 * - Date 实现了 Comparable，可以直接用 d1.compareTo(d2)，返回负/0/正
 * - 也可以用 d1.before(d2) / d1.after(d2)
 */
public class _02_DateCompareDemo {
    public static void main(String[] args) {
        Random r = new Random();
        Date d1 = new Date(Math.abs(r.nextInt()));
        Date d2 = new Date(Math.abs(r.nextInt()));

        // 方式一：getTime 比较
        if (d1.getTime() > d2.getTime()) {
            System.out.println("d1 在后，d2 在前");
        } else if (d1.getTime() < d2.getTime()) {
            System.out.println("d1 在前，d2 在后");
        } else {
            System.out.println("两者相等");
        }

        // 方式二：compareTo
        int c = d1.compareTo(d2);
        System.out.println("compareTo 结果（负数 d1<d2，0 相等，正数 d1>d2）：" + c);

        // 方式三：before / after
        System.out.println("d1.before(d2): " + d1.before(d2));
        System.out.println("d1.after(d2):  " + d1.after(d2));
    }
}
