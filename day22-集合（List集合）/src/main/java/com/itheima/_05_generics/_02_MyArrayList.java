package com.itheima._05_generics;

import java.util.Arrays;

/**
 * 知识点十六：自定义"泛型类"。
 *
 *  类名后面的 <E> 是"类型参数"，由调用方在 new 时确定。
 *  类内部 E 当作一个未知类型使用。
 *
 *  约定俗成：
 *      E - Element        T - Type
 *      K - Key            V - Value
 *      N - Number         R - Result
 */
public class _02_MyArrayList<E> {

    private Object[] data = new Object[10];
    private int size;

    public boolean add(E e) {
        data[size++] = e;
        return true;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) data[index];
    }

    public int size() { return size; }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(data, size));
    }
}
