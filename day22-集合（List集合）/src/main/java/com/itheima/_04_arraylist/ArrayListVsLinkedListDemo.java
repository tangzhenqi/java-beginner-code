package com.itheima._04_arraylist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 知识点十四（拓展）：ArrayList vs LinkedList 性能对比。
 *
 *  ArrayList   底层动态数组   随机访问 O(1)，中间增删 O(n)
 *  LinkedList  底层双向链表   随机访问 O(n)，头尾增删 O(1)
 *
 *  经验法则：
 *    - 默认无脑用 ArrayList。
 *    - 频繁在"头部"或"中间"插入/删除，且基本不按下标随机读，再考虑 LinkedList。
 *    - 实际上现在 Deque 场景更推荐 ArrayDeque，性能比 LinkedList 还好。
 */
public class ArrayListVsLinkedListDemo {
    public static void main(String[] args) {
        int n = 50_000;

        // ① 头插对比
        long t1 = System.nanoTime();
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) arr.add(0, i);
        long arrAdd = System.nanoTime() - t1;

        long t2 = System.nanoTime();
        List<Integer> linked = new LinkedList<>();
        for (int i = 0; i < n; i++) linked.add(0, i);
        long linkedAdd = System.nanoTime() - t2;

        System.out.printf("头插 %d 次:  ArrayList=%.2fms,  LinkedList=%.2fms%n",
                n, arrAdd / 1e6, linkedAdd / 1e6);

        // ② 随机读对比
        long t3 = System.nanoTime();
        long sumA = 0;
        for (int i = 0; i < n; i++) sumA += arr.get(i);
        long arrGet = System.nanoTime() - t3;

        long t4 = System.nanoTime();
        long sumL = 0;
        for (int i = 0; i < n; i++) sumL += linked.get(i);
        long linkedGet = System.nanoTime() - t4;

        System.out.printf("按索引读 %d 次: ArrayList=%.2fms, LinkedList=%.2fms%n",
                n, arrGet / 1e6, linkedGet / 1e6);
        System.out.println("(校验和: " + sumA + " / " + sumL + ")");
    }
}
