package com.itheima._02_hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 用 HashMap 做"投票计数"。
 *
 * 80 名同学从 A/B/C/D 四个景点中各选一个，统计哪个景点票数最多。
 *
 * 思路：键=景点名，值=票数；遍历每张票，存在则 +1，否则置 1。
 * 进阶写法见 _01_map.MapMergeDemo（merge 一行搞定）。
 */
public class HashMapCountDemo {
    public static void main(String[] args) {
        // 1. 模拟 80 张投票
        String[] arr = {"A", "B", "C", "D"};
        ArrayList<String> votes = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 80; i++) {
            votes.add(arr[r.nextInt(arr.length)]);
        }

        // 2. 统计
        HashMap<String, Integer> hm = new HashMap<>();
        for (String name : votes) {
            if (hm.containsKey(name)) {
                hm.put(name, hm.get(name) + 1);
            } else {
                hm.put(name, 1);
            }
        }
        System.out.println("各景点票数：" + hm);

        // 3. 求最大票数
        int max = 0;
        for (Map.Entry<String, Integer> e : hm.entrySet()) {
            if (e.getValue() > max) max = e.getValue();
        }

        // 4. 打印所有票数等于 max 的景点（处理并列第一的情况）
        System.out.print("最多被投票的景点：");
        for (Map.Entry<String, Integer> e : hm.entrySet()) {
            if (e.getValue() == max) {
                System.out.print(e.getKey() + " ");
            }
        }
        System.out.println("（" + max + " 票）");
    }
}
