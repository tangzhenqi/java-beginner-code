package com.itheima._01_generics;

import java.util.Arrays;

/**
 * 实现泛型接口的方式二：实现类继续保留泛型 <E>，
 * 把类型确定推迟到 new GenericMyListImpl<Xxx>() 时。
 */
public class GenericMyListImpl<E> implements MyList<E> {
    private Object[] data = new Object[10];
    private int size;

    @Override
    public boolean add(E e) {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
        data[size++] = e;
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) data[index];
    }

    @Override
    public int size() {
        return size;
    }
}
