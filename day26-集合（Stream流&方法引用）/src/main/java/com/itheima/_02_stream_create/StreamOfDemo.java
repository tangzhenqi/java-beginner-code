package com.itheima._02_stream_create;

import java.util.stream.Stream;

/**
 * 获取 Stream 的方式 4：零散数据 {@code Stream.of(T... values)}
 * <p>
 * 易错点：
 * - of 的形参是「可变参数」，可变参数等价于一个数组；
 * - 若传入的是 *基本类型数组*（如 int[]），它会被当成「一个元素」放入流；
 * - 若想拆开必须传引用类型数组（Integer[]）或直接使用 Arrays.stream(int[])。
 */
public class StreamOfDemo {
    public static void main(String[] args) {
        // 1. 零散值
        Stream.of(1, 2, 3, 4, 5).forEach(System.out::println);
        Stream.of("a", "b", "c").forEach(System.out::println);

        // 2. 引用类型数组 -> 正常拆分
        Integer[] arr1 = {1, 2, 3};
        Stream.of(arr1).forEach(System.out::println);

        // 3. 基本类型数组 -> 整体作为一个元素（容易踩坑）
        int[] arr2 = {1, 2, 3};
        Stream.of(arr2).forEach(e ->
                System.out.println("元素类型: " + e.getClass().getName())); // [I
    }
}
