package com.itheima._05_generics;

/**
 * 知识点十六（演示）：使用泛型类。
 *
 *  同一个 _02_MyArrayList<E> 模板，可以"实例化"成 String 版、Integer 版……
 *  类型由 new 时的 <...> 决定，编译期保证类型安全。
 */
public class _03_GenericClassDemo {
    public static void main(String[] args) {
        _02_MyArrayList<String> sl = new _02_MyArrayList<>();
        sl.add("aaa");
        sl.add("bbb");
        sl.add("ccc");
        System.out.println("String 版: " + sl);

        _02_MyArrayList<Integer> il = new _02_MyArrayList<>();
        il.add(123);
        il.add(456);
        il.add(789);

        int first = il.get(0); // 不需要强转
        System.out.println("Integer 版: " + il + ", get(0)=" + first);
    }
}
