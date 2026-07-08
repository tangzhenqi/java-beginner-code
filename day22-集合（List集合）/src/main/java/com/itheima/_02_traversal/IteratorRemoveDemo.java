package com.itheima._02_traversal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 知识点五：遍历中要删除元素，正确做法是用 迭代器自己的 remove()。
 *
 *  it.remove() 会同步更新 expectedModCount = modCount，所以不会抛 CME。
 *  注意：迭代器接口只提供了 remove，没有 add。要在遍历中插入元素，
 *       必须使用 ListIterator（见 _03_list/ListIteratorDemo）。
 *
 * 拓展：Java 8 之后，更优雅的做法是 collection.removeIf(predicate)，
 *      一行代码完成"按条件删除"。
 */
public class IteratorRemoveDemo {
    public static void main(String[] args) {
        Collection<String> coll = new ArrayList<>();
        coll.add("aaa");
        coll.add("bbb");
        coll.add("ccc");
        coll.add("ddd");
        coll.add("eee");

        Iterator<String> it = coll.iterator();
        while (it.hasNext()) {
            String str = it.next();
            if ("bbb".equals(str)) {
                it.remove(); // 正确做法
            }
        }
        System.out.println("迭代器删除后: " + coll);

        // 拓展：Java 8 removeIf（内部其实就是用 Iterator 实现的）
        coll.removeIf(s -> s.startsWith("c"));
        System.out.println("removeIf 后: " + coll);
    }
}
