package com.itheima._06_collections;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Collections.binarySearch：二分查找。
 *
 *   - 调用前 List 必须升序，否则结果不可预期
 *   - 找到返回索引（非负）
 *   - 找不到返回 -(插入点) - 1，可推算应插入位置：insertPoint = -result - 1
 */
public class CollectionsBinarySearchDemo {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println("找 9  -> " + Collections.binarySearch(list, 9));   // 8
        System.out.println("找 1  -> " + Collections.binarySearch(list, 1));   // 0
        System.out.println("找 20 -> " + Collections.binarySearch(list, 20));  // 负数

        // 拓展：用返回值推算应插入位置
        int r = Collections.binarySearch(list, 20);
        if (r < 0) {
            System.out.println("20 应插入到索引 " + (-r - 1));
        }
    }
}
