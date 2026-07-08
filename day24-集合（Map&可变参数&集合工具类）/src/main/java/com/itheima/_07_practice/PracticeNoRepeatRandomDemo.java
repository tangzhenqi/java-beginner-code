package com.itheima._07_practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * 练习三：不重复随机点名。
 *
 *   要求：被点到的学生本轮不再被点。
 *         全部点完后开启下一轮，依旧不重复。
 *
 * 思路：用两个集合做"抽到 -> 临时存放"，本轮抽完再合并回去。
 *      也可以直接 shuffle 一次，按顺序遍历，更简洁。
 */
public class PracticeNoRepeatRandomDemo {
    public static void main(String[] args) {
        ArrayList<String> pool = new ArrayList<>();
        Collections.addAll(pool,
                "范闲", "范建", "范统", "杜子腾", "杜琦燕",
                "宋合泛", "侯笼藤", "朱益群", "朱穆朗玛峰", "袁明媛");

        ArrayList<String> picked = new ArrayList<>();
        Random r = new Random();

        // 三轮演示
        for (int round = 1; round <= 3; round++) {
            System.out.println("======== 第 " + round + " 轮 ========");
            int count = pool.size();
            for (int i = 0; i < count; i++) {
                int idx = r.nextInt(pool.size());
                String name = pool.remove(idx);     // 抽走，下轮不再被抽
                picked.add(name);
                System.out.println("被点到：" + name);
            }
            // 一轮结束，把抽走的都还回去
            pool.addAll(picked);
            picked.clear();
        }
    }
}
