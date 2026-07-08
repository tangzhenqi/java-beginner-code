package com.itheima._07_practice;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 练习一：随机点名器。
 *
 * 班级里有 N 个学生（这里只用名字），实现随机点一个。
 *
 * 思路：Collections.shuffle 把集合打乱，取第 0 个即可。
 *      也可以 Random.nextInt(list.size()) 取索引。
 */
public class PracticeRandomNameDemo {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,
                "范闲", "范建", "范统", "杜子腾", "杜琦燕",
                "宋合泛", "侯笼藤", "朱益群", "朱穆朗玛峰", "袁明媛");

        Collections.shuffle(list);
        System.out.println("被点到：" + list.get(0));
    }
}
