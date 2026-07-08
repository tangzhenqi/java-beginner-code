package com.itheima._05_generics;

import java.util.Arrays;

/**
 * 实现方式①：实现接口时直接"写死"为 String，类本身不再是泛型。
 */
public class _07_StringList implements _06_MyList<String> {

    private Object[] data = new Object[10];
    private int size;

    @Override
    public boolean add(String s) {
        data[size++] = s;
        return true;
    }

    @Override
    public String get(int index) {
        return (String) data[index];
    }

    @Override
    public int size() { return size; }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(data, size));
    }
}
