package com.itheima._02_stream_create;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 获取 Stream 的方式 3：数组 {@code Arrays.stream(array)}
 * <p>
 * 拓展：基本数据类型有专用流，避免装箱拆箱的性能损耗：
 * - IntStream / LongStream / DoubleStream
 * 它们提供了 sum() / average() / max() 等便捷统计方法。
 */
public class StreamFromArrayDemo {
    public static void main(String[] args) {
        // 引用类型数组：返回 Stream<String>
        String[] arr2 = {"a", "b", "c"};
        Arrays.stream(arr2).forEach(System.out::println);

        // 基本类型数组：Arrays.stream(int[]) 返回 IntStream
        int[] arr1 = {1, 2, 3, 4, 5};
        IntStream intStream = Arrays.stream(arr1);
        // IntStream 提供专用统计方法
        System.out.println("sum   = " + Arrays.stream(arr1).sum());
        System.out.println("avg   = " + Arrays.stream(arr1).average().orElse(0));
        System.out.println("max   = " + Arrays.stream(arr1).max().getAsInt());
        System.out.println("count = " + intStream.count());

        // IntStream -> Stream<Integer> 装箱
        Arrays.stream(arr1).boxed().forEach(System.out::println);
    }
}
