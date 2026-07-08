package com.itheima._02_traversal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 知识点三：Collection 通用遍历方式之 1 —— 迭代器 Iterator。
 *
 *  Iterator<E> iterator()  获取迭代器对象（默认指向 0 索引）
 *  boolean     hasNext()   当前指针位置是否有元素
 *  E           next()      取出当前元素 + 移动指针
 *
 * 迭代器是 Collection 体系最通用的遍历方式（List/Set 都能用）。
 */
public class IteratorDemo {
    public static void main(String[] args) {
        Collection<String> coll = new ArrayList<>();
        coll.add("aaa");
        coll.add("bbb");
        coll.add("ccc");
        coll.add("ddd");

        // 用接口类型声明变量: 左边 Iterator 是接口, 右边返回的是实现了该接口的具体类对象
        // 好处：只依赖接口约定(hasNext/next),不关心具体实现,换集合也不用改这行代码.
        Iterator<String> it = coll.iterator();
        while (it.hasNext()) {
            String str = it.next();
            System.out.println(str);
        }
    }
}


// List<String> list = new ArrayList<>();   // 左边接口 List，右边实现类 ArrayList
// Map<String, Integer> map = new HashMap<>(); // 左边接口 Map，右边实现类 HashMap