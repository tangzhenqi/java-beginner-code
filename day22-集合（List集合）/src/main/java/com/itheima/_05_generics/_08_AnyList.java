package com.itheima._05_generics;

import java.util.Arrays;

/**
 * 实现方式②：实现类继续保留泛型，把类型决定权延迟到 new 时。
 */
public class _08_AnyList<E> implements _06_MyList<E> {

    private Object[] data = new Object[10];
    private int size;

    @Override
    public boolean add(E e) {
        data[size++] = e;
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) data[index];
    }

    @Override
    public int size() { return size; }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(data, size));
    }
}
