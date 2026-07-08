package com.itheima._01_generics;

import java.util.ArrayList;

public class _03_GenericMethod {
    public static void main(String[] args) {
        // 1) 字符串集合
        ArrayList<String> strs = new ArrayList<>();
        ListUtil.addAll(strs, "aaa", "bbb", "ccc", "ddd");
        System.out.println(strs);

        // 2) 整数集合
        ArrayList<Integer> nums = new ArrayList<>();
        ListUtil.addAll(nums, 1, 2, 3, 4, 5);
        System.out.println(nums);

        // 3) 拓展方法 max
        System.out.println("最大值 = " + ListUtil.max(nums));

        // 4) 拓展方法 swap
        ListUtil.swap(strs, 0, strs.size() - 1);
        System.out.println("交换首尾后：" + strs);
    }
}
