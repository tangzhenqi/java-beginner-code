package com.itheima._04_stream_terminal;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 拓展：min / max / findFirst / anyMatch / allMatch / noneMatch
 * <p>
 * 这些都是「短路」终结操作：满足条件后立刻退出，不会遍历整条流。
 */
public class _07_StreamMinMaxFindMatchDemo {
    public static void main(String[] args) {
        Optional<Integer> max = Stream.of(3, 1, 4, 1, 5, 9, 2).max(Comparator.naturalOrder());
        Optional<Integer> min = Stream.of(3, 1, 4, 1, 5, 9, 2).min(Comparator.naturalOrder());
        System.out.println("max = " + max.get() + ", min = " + min.get());

        // findFirst：取第一个；常配合 filter
        Stream.of("apple", "banana", "blueberry")
                .filter(s -> s.startsWith("b"))
                .findFirst()
                .ifPresent(s -> System.out.println("first b: " + s));

        // anyMatch / allMatch / noneMatch 返回 boolean
        boolean any = Stream.of(1, 2, 3).anyMatch(i -> i > 2);
        boolean all = Stream.of(1, 2, 3).allMatch(i -> i > 0);
        boolean none = Stream.of(1, 2, 3).noneMatch(i -> i < 0);
        System.out.println("any > 2: " + any + ", all > 0: " + all + ", none < 0: " + none);
    }
}
