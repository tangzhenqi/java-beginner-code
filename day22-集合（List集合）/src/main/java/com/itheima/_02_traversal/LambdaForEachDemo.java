package com.itheima._02_traversal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

/**
 * 知识点七：Collection 通用遍历方式之 3 —— Lambda forEach。
 *
 *  default void forEach(Consumer<? super T> action)
 *
 * 底层：实现类（如 ArrayList）会内部循环，依次把每个元素传给
 * Consumer.accept(t)。Lambda 表达式就是 Consumer 这个函数式接口的简化写法。
 *
 * 拓展：进一步可用 方法引用 让代码更短：
 *      coll.forEach(System.out::println);
 *
 *      想"边遍历边产生新数据"或"过滤+收集"，应转 Stream（day24+）。
 */
public class LambdaForEachDemo {
    public static void main(String[] args) {
        Collection<String> coll = new ArrayList<>();
        coll.add("zhangsan");
        coll.add("lisi");
        coll.add("wangwu");

        // ① 匿名内部类
        coll.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("[匿名内部类] " + s);
            }
        });

        // ② Lambda 简化
        coll.forEach(s -> System.out.println("[Lambda] " + s));

        // ③ 方法引用进一步简化
        coll.forEach(System.out::println);
    }
}
