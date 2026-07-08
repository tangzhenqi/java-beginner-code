package com.summary.day15.innerclass.iterator;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 【进阶版】更接近 JDK 真实实现的迭代器。
 *
 * 在简化版 MyList.Iterator 的基础上，补齐了 ArrayList.Itr 的三个"生产级"特性：
 *   1. implements java.util.Iterator<E> —— 从而能被 for-each（增强 for）使用。
 *   2. 支持 remove() —— 靠 lastRet 记住"上一个返回的下标"。
 *   3. fail-fast 快速失败 —— 靠 modCount / expectedModCount 检测遍历期间的结构性修改，
 *      一旦发现就抛 ConcurrentModificationException。
 *
 * 核心设计与简化版一致：迭代器是【非静态成员内部类】，隐式持有外部 MyArrayList 实例，
 * 因此能随时读到外部最新的 elementData / size / modCount。
 * 这也正是它能实现 fail-fast 的前提 —— static 内部类不持有外部引用，做不到。
 *
 * 注意：本类实现 Iterable，所以可直接用增强 for 遍历。
 */
public class JdkLikeIteratorDemo {

    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        System.out.println("======== 1. 增强 for（底层就是调用 iterator()）========");
        for (String s : list) {
            System.out.println("元素：" + s);
        }

        System.out.println("\n======== 2. 手动用 Iterator + remove() 删除元素 ========");
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String s = it.next();
            if (s.equals("b")) {
                it.remove();   // 安全删除：通过迭代器自己的 remove
            }
        }
        System.out.println("删除 b 之后：" + list);

        System.out.println("\n======== 3. fail-fast：遍历中途结构性修改 → 抛异常 ========");
        try {
            for (String s : list) {
                System.out.println("正在遍历：" + s);
                list.add("X");   // 遍历期间直接改 list 结构（绕过迭代器）
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("捕获到 ConcurrentModificationException —— 这就是 fail-fast 在起作用");
        }
    }
}

/**
 * 一个极简但结构贴近 ArrayList 的动态数组，用泛型 <E>。
 * 实现 Iterable<E> 以支持增强 for。
 */
class MyArrayList<E> implements Iterable<E> {

    private Object[] elementData;   // 真实 ArrayList 也叫这个名字
    private int size;
    private int modCount;           // 结构性修改次数（add/remove 会 ++）

    MyArrayList() {
        elementData = new Object[10];
    }

    void add(E e) {
        ensureCapacity();
        elementData[size++] = e;
        modCount++;                 // 结构变了，计数 +1
    }

    private void ensureCapacity() {
        if (size == elementData.length) {
            // 扩容为原来的 1.5 倍，和 ArrayList 思路一致
            elementData = Arrays.copyOf(elementData, elementData.length + (elementData.length >> 1));
        }
    }

    /** 按下标删除：会移动后续元素，并让 modCount++ */
    void removeAt(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[--size] = null;   // 释放引用，帮助 GC
        modCount++;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elementData[i]);
            if (i < size - 1) sb.append(", ");
        }
        return sb.append("]").toString();
    }

    /**
     * 非静态成员内部类 —— 和 ArrayList.Itr 同款设计。
     * 它直接访问的 elementData / size / modCount，都是外部 MyArrayList.this 的字段。
     */
    private class Itr implements Iterator<E> {
        int cursor;                            // 下一个要返回的下标
        int lastRet = -1;                      // 上一个返回的下标，没有则为 -1（供 remove 用）
        int expectedModCount = modCount;       // 创建迭代器时，记录外部状态的"快照"

        @Override
        public boolean hasNext() {
            return cursor != size;             // 读外部实例的 size
        }

        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            checkForComodification();          // 每次取元素前先校验有没有被偷改
            if (cursor >= size) {
                throw new NoSuchElementException();
            }
            int i = cursor;
            cursor = i + 1;
            lastRet = i;                       // 记住这次返回的位置
            return (E) elementData[i];
        }

        @Override
        public void remove() {
            if (lastRet < 0) {
                // 没有先 next() 就 remove，或连续 remove 两次 —— 非法
                throw new IllegalStateException();
            }
            checkForComodification();
            MyArrayList.this.removeAt(lastRet); // 通过外部对象删除（显式写出 MyArrayList.this）
            cursor = lastRet;                   // 游标回退，避免跳过下一个元素
            lastRet = -1;
            expectedModCount = modCount;        // 自己删的，更新快照，使后续遍历不报错
        }

        /** fail-fast 的核心：外部 modCount 与创建时的快照不一致，说明有人绕过迭代器改了结构 */
        private void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
