package com.itheima._01_generics;

import java.util.Arrays;

/*
 * 知识点 2：泛型类
 *
 * 定义格式：修饰符 class 类名<类型参数1, 类型参数2, ...>
 *      类型参数（也叫类型变量）：E、T、K、V 等只是约定俗成的名字
 *          E - Element  （集合元素）
 *          T - Type     （任意类型）
 *          K - Key      （键）
 *          V - Value    （值）
 *          N - Number   （数字）
 *          R - Result   （返回值）
 *
 * 使用场景：
 *      类内部某些字段、方法参数、返回值的类型在"写代码"的时候还不能确定，
 *      但希望调用者在"创建对象"的时候来决定。
 *
 * 注意：
 *      泛型不能直接 new，例如 new E()、new E[10] 都是不允许的（类型擦除导致）。
 *      所以这里底层用 Object[] 数组，取出时强转回 E。
 */
public class MyArrayList<E> {

    private Object[] data = new Object[10];
    private int size;

    public boolean add(E e) {
        ensureCapacity();
        data[size++] = e;
        return true;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E) data[index];     // 编译器在调用处自动强转，业务代码无感知
    }

    @SuppressWarnings("unchecked")
    public E remove(int index) {
        checkIndex(index);
        E old = (E) data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[--size] = null;
        return old;
    }

    public int size() {
        return size;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index=" + index + ", size=" + size);
        }
    }

    private void ensureCapacity() {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2);  // 扩容为 2 倍
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(data, size));
    }
}
