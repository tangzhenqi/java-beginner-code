package com.itheima._01_generics;

import java.util.Arrays;

/**
 * 实现泛型接口的方式一：实现类直接给出具体类型（这里固化为 String）。
 * 以后这个类只能装字符串。
 */
public class StringMyListImpl implements MyList<String> {
    private String[] data = new String[10];
    private int size;

    @Override
    public boolean add(String s) {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
        data[size++] = s;
        return true;
    }

    @Override
    public String get(int index) {
        return data[index];
    }

    @Override
    public int size() {
        return size;
    }
}
