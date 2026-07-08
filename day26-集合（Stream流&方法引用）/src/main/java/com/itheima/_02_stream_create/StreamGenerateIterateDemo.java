package com.itheima._02_stream_create;

import java.util.stream.Stream;

/**
 * 拓展：无限流的两种构造方式
 * <p>
 * - {@code Stream.iterate(seed, UnaryOperator)}：根据上一个元素递推下一个，必须配合 limit 截断。
 * - {@code Stream.generate(Supplier)}：每次调用 Supplier 产生新元素，常用于随机数流。
 * <p>
 * 没有 limit / takeWhile 会无限循环。
 */
public class StreamGenerateIterateDemo {
    public static void main(String[] args) {
        // iterate：等差数列 1,3,5,7,...
        Stream.iterate(1, x -> x + 2)
                .limit(5)
                .forEach(System.out::println);

        System.out.println("---");

        // generate：5 个随机数
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }
}
