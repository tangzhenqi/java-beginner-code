package com.itheima._04_treemap;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * TreeMap：基于红黑树，键有序。
 *
 * 默认排序：
 *   - Integer / Double 按数值升序
 *   - String 按字符 ASCII 升序（abcdefg...）
 *
 * 自定义规则：构造器传入 Comparator。
 *   compare(o1, o2) 返回值含义：
 *     o1 < o2 -> 负数（升序时 o1 排前）
 *     o1 > o2 -> 正数
 *     o1 == o2 -> 0（视为重复键，覆盖值）
 */
public class TreeMapComparatorDemo {
    public static void main(String[] args) {
        // 需求 1：键 id 升序（默认行为）
        TreeMap<Integer, String> asc = new TreeMap<>();
        asc.put(5, "可恰可乐");
        asc.put(4, "雷碧");
        asc.put(3, "九个核桃");
        asc.put(2, "康帅傅");
        asc.put(1, "粤利粤");
        System.out.println("升序：" + asc);

        // 需求 2：键 id 降序，使用 Comparator
        TreeMap<Integer, String> desc = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // o1 当前要添加的；o2 已经在红黑树中的
                return o2 - o1;
            }
        });
        desc.put(5, "可恰可乐");
        desc.put(4, "雷碧");
        desc.put(3, "九个核桃");
        desc.put(2, "康帅傅");
        desc.put(1, "粤利粤");
        System.out.println("降序：" + desc);

        // lambda 写法
        TreeMap<Integer, String> desc2 = new TreeMap<>((o1, o2) -> o2 - o1);
        desc2.put(10, "x");
        desc2.put(20, "y");
        desc2.put(5,  "z");
        System.out.println("lambda 降序：" + desc2);
    }
}
