package com.itheima._04_stream_terminal;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * 拓展：reduce —— 归约
 * <p>
 * 把流中的多个元素「折叠」成一个结果：求和、求积、拼接、找最大…等。
 * 三个重载：
 *   - reduce(BinaryOperator)              ：无初始值，返回 Optional
 *   - reduce(identity, BinaryOperator)    ：有初始值，返回同类型
 *   - reduce(identity, accumulator, combiner)：并行流时使用
 */
public class _08_StreamReduceDemo {
    public static void main(String[] args) {
        // 1. 求和（无初值）
        Optional<Integer> sum = Stream.of(1, 2, 3, 4, 5).reduce(Integer::sum);
        System.out.println("sum = " + sum.orElse(0));

        // 2. 求和（有初值）
        int sum2 = Stream.of(1, 2, 3, 4, 5).reduce(0, Integer::sum);
        System.out.println("sum2 = " + sum2);

        // 3. 求最大值
        Integer max = Stream.of(3, 1, 4, 1, 5, 9, 2, 6).reduce(Integer.MIN_VALUE, Math::max);
        System.out.println("max = " + max);

        // 4. 字符串拼接
        String joined = Stream.of("a", "b", "c").reduce("", String::concat);
        System.out.println(joined);
    }
}
