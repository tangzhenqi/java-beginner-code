package com.itheima._05_generics;

/**
 * 知识点十八（演示）：泛型接口的两种实现方式对比。
 */
public class _09_GenericInterfaceDemo {
    public static void main(String[] args) {
        // 方式①：实现时写死类型
        _07_StringList sl = new _07_StringList();
        sl.add("aaa");
        sl.add("bbb");
        System.out.println("StringList: " + sl);

        // 方式②：实现类延续泛型
        _08_AnyList<Integer> al = new _08_AnyList<>();
        al.add(1);
        al.add(2);
        System.out.println("AnyList<Integer>: " + al);

        _08_AnyList<String> al2 = new _08_AnyList<>();
        al2.add("x");
        al2.add("y");
        System.out.println("AnyList<String>: " + al2);
    }
}
