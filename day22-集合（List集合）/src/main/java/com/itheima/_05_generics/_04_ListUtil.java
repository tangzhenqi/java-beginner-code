package com.itheima._05_generics;

import java.util.ArrayList;

/**
 * 知识点十七：泛型方法（注意 <E> 写在"返回值之前"）。
 *
 *  - 静态方法不能用"类上的泛型"，因为类上的 E 是在 new 对象时才确定的，
 *    而静态方法不依赖对象。要让静态方法支持泛型，必须在方法上独立声明 <E>。
 *  - 可变参数 E... 可以替代固定个数的形参，更通用。
 */
public class _04_ListUtil {

    private _04_ListUtil() {}

    /** 可变参数泛型方法：一次往集合中加入若干元素。 */
    @SafeVarargs
    public static <E> void addAll(ArrayList<E> list, E... elements) {
        for (E e : elements) {
            list.add(e);
        }
    }
}
