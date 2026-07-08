package com.itheima._01_generics;

import java.util.ArrayList;
import java.util.Collections;

/*
 * 知识点 3：泛型方法
 *
 * 定义格式：修饰符 <类型参数> 返回值类型 方法名(参数列表) { ... }
 *      关键：类型参数声明必须写在"返回值类型之前"，与类上的泛型互相独立。
 *
 * 与泛型类中"方法用类的泛型"的区别：
 *      泛型类：E 在 new 对象时确定。
 *      泛型方法：T 在每次调用方法时由实参类型独立确定，更灵活。
 */
public class ListUtil {

    private ListUtil() {}

    /**
     * 把若干个元素全部加入集合中（可变参数版）。
     * 这里 <E> 必须写在返回值 void 之前。
     */
    @SafeVarargs
    public static <E> void addAll(ArrayList<E> list, E... elements) {
        for (E e : elements) {
            list.add(e);
        }
    }

    /**
     * 拓展：找出集合中最大的元素。
     * <T extends Comparable<T>> 限定类型必须可比较，否则没办法定义"最大"。
     */
    public static <T extends Comparable<T>> T max(ArrayList<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("集合不能为空");
        }
        T max = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).compareTo(max) > 0) {
                max = list.get(i);
            }
        }
        return max;
    }

    /**
     * 拓展：交换集合中的两个元素位置。
     * 体现"泛型方法可以独立于类的泛型工作"。
     */
    public static <E> void swap(ArrayList<E> list, int i, int j) {
        Collections.swap(list, i, j);
    }
}
