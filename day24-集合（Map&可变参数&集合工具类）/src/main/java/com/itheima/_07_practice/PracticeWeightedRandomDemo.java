package com.itheima._07_practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * 练习二：带权重的随机点名。
 *
 *   要求：70% 概率点到男生，30% 概率点到女生。
 *
 * 思路：构造一个"选男 / 选女"的集合（按比例放入 7 个 1 和 3 个 0），
 *      打乱后随机取一个，决定从哪个名单里再随机抽人。
 *
 * 进阶：见 day30 带权重随机的更通用写法。
 */
public class PracticeWeightedRandomDemo {
    public static void main(String[] args) {
        // 1. 决定从哪个性别抽（按 7:3 放比例）
        ArrayList<Integer> flag = new ArrayList<>();
        Collections.addAll(flag, 1, 1, 1, 1, 1, 1, 1);   // 男
        Collections.addAll(flag, 0, 0, 0);               // 女
        Collections.shuffle(flag);

        Random r = new Random();
        int pick = flag.get(r.nextInt(flag.size()));

        // 2. 男 / 女 各自的名单
        ArrayList<String> boys  = new ArrayList<>();
        ArrayList<String> girls = new ArrayList<>();
        Collections.addAll(boys,  "范闲", "范建", "范统", "杜子腾", "宋合泛", "侯笼藤", "朱益群", "朱穆朗玛峰");
        Collections.addAll(girls, "杜琦燕", "袁明媛", "李猜", "田蜜蜜");

        // 3. 抽人
        if (pick == 1) {
            System.out.println("男生被点到：" + boys.get(r.nextInt(boys.size())));
        } else {
            System.out.println("女生被点到：" + girls.get(r.nextInt(girls.size())));
        }
    }
}
