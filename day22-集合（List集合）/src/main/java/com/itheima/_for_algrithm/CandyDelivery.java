package com.itheima._for_algrithm;

import java.util.ArrayList;
import java.util.Collections;

public class CandyDelivery {
    public static void main(String[] args) {
        ArrayList<Integer> candyCounts = new ArrayList<>(Collections.nCopies(100, 0));
        System.out.println(candyCounts.size()); // 100
        System.out.println(candyCounts.get(0)); // 0
    }

    public static long minDifference(long[] input) {
        int n = input.length;
        // 转为 1-based 数组, 索引 1..n
        long[] a = new long[n + 1];
        for (int i = 0; i < n; i++) {
            a[i + 1] = input[i];
        }

        // 前缀和，prefix[i] = a[1] - a[2] + ... ± a[i]
        long[] prefix = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            long sign = (i % 2 == 1) ? 1 : -1;  // Alice: + , Bob: -
            prefix[i] = prefix[i - 1] + sign * a[i];
        }

        long S = prefix[n];
        long answer = Math.abs(S);

        // 枚举拿走第 k 堆
        for (int k = 1; k <= n; k++) {
            long newDiff = prefix[k - 1] + prefix[k] - S;
            answer = Math.min(answer, Math.abs(newDiff));
        }
        return answer;
    }
}



