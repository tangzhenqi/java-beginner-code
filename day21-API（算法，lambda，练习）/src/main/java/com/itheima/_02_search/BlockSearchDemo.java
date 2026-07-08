package com.itheima._02_search;

/**
 * 分块查找 (Block Search / 索引顺序查找)
 *
 * 核心思想：块内无序，块间有序
 *   - 把整个数组划分为若干"块"，每一块的最大值小于下一块的最小值；
 *   - 用一个 Block[] 索引表保存每块的 max、startIndex、endIndex；
 *   - 先在索引表上确定目标属于哪一块（顺序或二分），再在块内顺序查找。
 *
 * 适用场景：数据量大、动态插入较多（无需全数组有序），用分块来平衡查询与维护开销。
 * 复杂度：平均 O(√n)（当块大小约为 √n 时最优）。
 */
public class BlockSearchDemo {
    public static void main(String[] args) {
        int[] arr = {16, 5, 9, 12, 21, 18,
                     32, 23, 37, 26, 45, 34,
                     50, 48, 61, 52, 73, 66};

        Block[] blockArr = {
                new Block(21, 0, 5),
                new Block(45, 6, 11),
                new Block(73, 12, 17)
        };

        System.out.println("找 37: 索引=" + getIndex(blockArr, arr, 37));
        System.out.println("找 99: 索引=" + getIndex(blockArr, arr, 99));
    }

    private static int getIndex(Block[] blockArr, int[] arr, int number) {
        int indexBlock = findIndexBlock(blockArr, number);
        if (indexBlock == -1) return -1;

        int startIndex = blockArr[indexBlock].getStartIndex();
        int endIndex = blockArr[indexBlock].getEndIndex();
        for (int i = startIndex; i <= endIndex; i++) {
            if (arr[i] == number) return i;
        }
        return -1;
    }

    /** 在索引表中定位 number 应该属于的块（顺序查找版） */
    private static int findIndexBlock(Block[] blockArr, int number) {
        // 先判断是否大于最后一块 max → 一定不存在
        if (number > blockArr[blockArr.length - 1].getMax()) return -1;
        for (int i = 0; i < blockArr.length; i++) {
            if (number <= blockArr[i].getMax()) return i;
        }
        return -1;
    }
}
