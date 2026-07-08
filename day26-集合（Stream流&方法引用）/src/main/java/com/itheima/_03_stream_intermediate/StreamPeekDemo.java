package com.itheima._03_stream_intermediate;

import java.util.stream.Stream;

/**
 * 拓展中间方法：peek(Consumer)
 * <p>
 * 与 forEach 几乎一样，但 peek 是「中间方法」，主要用于调试 / 观察流水线中的元素。
 * forEach 是终结方法，调用后流就关闭了。
 */
public class StreamPeekDemo {
    public static void main(String[] args) {
        long count = Stream.of("a", "bb", "ccc", "dddd")
                .peek(s -> System.out.println("[before filter] " + s))
                .filter(s -> s.length() > 1)
                .peek(s -> System.out.println("[after filter ] " + s))
                .count();
        System.out.println("剩下 " + count + " 个元素");
    }
}
