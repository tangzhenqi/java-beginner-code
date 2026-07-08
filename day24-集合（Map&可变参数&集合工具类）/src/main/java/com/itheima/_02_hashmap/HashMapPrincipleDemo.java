package com.itheima._02_hashmap;

import java.util.HashMap;

/**
 * HashMap 原理简述（面试常考）。
 *
 *  1. 底层结构：JDK8 起 = 数组 + 链表 + 红黑树
 *     - 数组：默认长度 16，每个位置叫"桶/bucket"
 *     - 链表：哈希冲突时同一个桶里的元素串成链表
 *     - 红黑树：链表长度 > 8 且 数组长度 >= 64 时，转为红黑树以加速查找
 *
 *  2. put(key, value) 流程：
 *     - 计算 key.hashCode()，再做扰动 (h ^ (h >>> 16))，与 (n-1) 取模得到下标
 *     - 如果该桶为空，直接放入
 *     - 如果该桶有元素，先比 hashCode 再比 equals
 *        相同 -> 覆盖；不同 -> 链表 / 红黑树尾插
 *
 *  3. 扩容（resize）：
 *     - 加载因子 0.75
 *     - 元素个数超过 容量 * 0.75，数组扩容为原来 2 倍，原数据重新散列
 *
 *  4. 为什么自定义对象要重写 hashCode 和 equals：
 *     - hashCode 决定桶下标
 *     - equals 决定同桶内是否"相等"
 *     - 不重写则两个内容相同的对象会被视为不同 key
 */
public class HashMapPrincipleDemo {
    public static void main(String[] args) {
        // 演示一下 hashCode 决定桶下标
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("aaa", 111);
        hm.put("bbb", 222);
        hm.put("ccc", 333);

        for (String key : hm.keySet()) {
            int hash = key.hashCode();
            int disturbed = hash ^ (hash >>> 16);
            int index = disturbed & (16 - 1);     // 默认容量 16
            System.out.println(key + " hash=" + hash + " 桶下标=" + index);
        }
    }
}
