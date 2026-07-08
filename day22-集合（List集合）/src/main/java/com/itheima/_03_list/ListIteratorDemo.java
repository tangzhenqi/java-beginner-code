package com.itheima._03_list;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * 知识点十一：ListIterator —— List 独有的"列表迭代器"。
 *
 *  相比 Iterator 多了：
 *      add(E e)              在当前指针位置插入元素
 *      set(E e)              修改 next() 刚返回的那个元素
 *      hasPrevious()/previous()  支持反向遍历
 *
 *  能在遍历过程中安全地"插入"元素（普通 Iterator 不行）。
 */
public class ListIteratorDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        ListIterator<String> it = list.listIterator();
        while (it.hasNext()) {
            String str = it.next();
            if ("bbb".equals(str)) {
                it.add("qqq"); // 在 bbb 之后插入 qqq
            }
        }
        System.out.println("插入后: " + list); // [aaa, bbb, qqq, ccc]

        // 拓展：反向遍历（指针从末尾回退）
        System.out.print("反向遍历: ");
        while (it.hasPrevious()) {
            System.out.print(it.previous() + " ");
        }
        System.out.println();
    }
}
