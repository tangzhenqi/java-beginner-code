package com.itheima._02_traversal;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

/**
 * 知识点四：迭代器的四个易错点。
 *
 *  1. 越界后再调 next() -> NoSuchElementException
 *  2. 遍历完毕，指针不复位（要再遍历必须重新 iterator()）
 *  3. 一次循环里只能调用一次 next()（否则可能跳元素 / 越界）
 *  4. 遍历过程中用"集合自己的"add/remove 修改 -> ConcurrentModificationException（CME）
 *     原因（拓展）：ArrayList 内部维护 modCount，迭代器初始化时记录 expectedModCount，
 *                   集合自身的修改会改 modCount，迭代器下次调用 next() 时
 *                   checkForComodification() 发现两者不一致就抛 CME（"快速失败"机制）。
 */
public class IteratorPitfallDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        // 错误 1：再次 next 报 NoSuchElementException
        // System.out.println(it.next());

        // 错误 2：指针不会复位，hasNext() 永远 false，再用必须新建
        System.out.println("已遍历完,hasNext=" + it.hasNext());

        // 错误 4：演示 CME（快速失败）。先注释打开看现象。
        try {
            Iterator<String> it2 = list.iterator();
            while (it2.hasNext()) {
                String s = it2.next();
                if ("bbb".equals(s)) {
                    list.remove(s); // 用集合自身的方法 -> 触发 CME
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("捕获到 ConcurrentModificationException(这是预期行为)");
        }
    }
}
