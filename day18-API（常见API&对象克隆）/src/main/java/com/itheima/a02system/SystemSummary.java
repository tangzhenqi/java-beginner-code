package com.itheima.a02system;

/**
 * 知识点二：System 类
 * <p>
 * 归纳（day18 核心 API）：
 *   System.exit(int status)            终止 JVM。0 表示正常停止，非 0 表示异常停止。
 *   System.currentTimeMillis()         当前系统时间（自 1970-01-01 00:00:00 GMT 起的毫秒）。
 *   System.arraycopy(src, srcPos, dst, dstPos, length)  数组拷贝（底层是 native 实现，效率高）。
 * <p>
 * 特点：System 也是工具类，所有方法都是 static。
 */
public class SystemSummary {
    public static void main(String[] args) {
        // 1. currentTimeMillis 常用于做性能计时
        long start = System.currentTimeMillis();
        long sum = 0;
        for (int i = 1; i <= 1_000_000; i++) sum += i;
        long end = System.currentTimeMillis();
        System.out.println("sum = " + sum + "，耗时 " + (end - start) + " ms");

        // 2. arraycopy 演示
        int[] src = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] dst = new int[10];

        // 把 src[6..8] 拷贝到 dst[2..4]
        System.arraycopy(src, 6, dst, 2, 3);  // dst => 0 0 7 8 9 0 0 0 0 0
        printArr(dst);

        // 3. exit：通常在程序需要立刻终止时使用（例如拼图小游戏点了"退出"按钮）。
        //    放在最后避免后续代码无法执行。
        //    System.exit(0);
    }

    private static void printArr(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(arr[i]);
        }
        System.out.println(sb.append("]"));
    }
}
