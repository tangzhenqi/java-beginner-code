package com.itheima._06_collections;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Collections 工具类：addAll / shuffle。
 *
 *   public static <T> boolean addAll(Collection<T> c, T... elements)   批量添加
 *   public static void shuffle(List<?> list)                            随机打乱 List
 *
 * 注意：Collections 是 java.util 包下专门操作集合的工具类，
 *      不要和顶层接口 Collection 混淆。
 */
public class CollectionsAddAllShuffleDemo {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        // 一次性批量添加，比反复调用 add 简洁很多
        Collections.addAll(list, "abc", "bcd", "qwer", "df", "asdf", "zxcv", "1234");
        System.out.println("addAll  后：" + list);

        Collections.shuffle(list);
        System.out.println("shuffle 后：" + list);
    }
}
