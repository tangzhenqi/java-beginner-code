package com.itheima._02_hashmap;

import java.util.HashMap;

/**
 * HashMap 基础。
 *
 *   - 底层：哈希表（JDK8 之后是 数组 + 链表 + 红黑树）
 *   - 特点：键唯一、无序、无索引、允许 null 键和 null 值
 *   - 性能：增删查改 平均 O(1)
 *
 * 它就是 Map 接口最常用的实现类。
 */
public class HashMapBasicDemo {
    public static void main(String[] args) {
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("aaa", 111);
        hm.put("bbb", 222);
        hm.put("ccc", 333);
        hm.put(null,  -1);     // 允许 null 键
        hm.put("ddd", null);   // 允许 null 值

        // 同一个键再次 put -> 覆盖旧值
        hm.put("aaa", 999);

        System.out.println("HashMap 内容：" + hm);
        System.out.println("get(null) = " + hm.get(null));
        System.out.println("get(aaa)  = " + hm.get("aaa"));
    }
}
