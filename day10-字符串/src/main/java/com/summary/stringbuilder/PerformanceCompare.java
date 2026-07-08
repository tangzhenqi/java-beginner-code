package com.summary.stringbuilder;

/**
 * 性能对比：String + 拼接  vs  StringBuilder.append。
 *
 * 跑一下你会发现两者耗时差距数量级（百倍以上）。
 * 结论：循环里有字符串拼接，请用 StringBuilder。
 *
 * 拓展：JDK 5 之后，编译器会自动把"非循环里的 + 拼接"优化成 StringBuilder，
 *       但"循环体中的 +"每轮还是会 new 一个 StringBuilder，依然慢。
 */
public class PerformanceCompare {
    public static void main(String[] args) {
        int n = 100000;

        long t1 = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < n; i++) {
            s = s + "a";
        }
        long t2 = System.currentTimeMillis();
        System.out.println("String + 用时：" + (t2 - t1) + " ms");

        long t3 = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("a");
        }
        long t4 = System.currentTimeMillis();
        System.out.println("StringBuilder.append 用时：" + (t4 - t3) + " ms");
    }
}
