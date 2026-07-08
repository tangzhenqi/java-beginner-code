package com.itheima._01_collection;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 知识点二：自定义对象与 contains / remove 的关系。
 *
 *  contains 底层调用 element.equals(target) 判断；
 *  如果 Javabean 没有重写 equals，调用的是 Object.equals（比较地址）。
 *
 * 拓展：
 *  - 重写 equals 时，强烈建议同时重写 hashCode，
 *    否则同一个对象可能在 HashMap/HashSet 中表现"等价但不相等"，
 *    并破坏 equals/hashCode 通用契约（这点在 day23 Set/HashMap 中尤其重要）。
 */
public class CollectionEqualsDemo {
    public static void main(String[] args) {
        Collection<Student> coll = new ArrayList<>();
        coll.add(new Student("zhangsan", 23));
        coll.add(new Student("lisi", 24));
        coll.add(new Student("wangwu", 25));

        // 一个新对象，地址不同但内容相同
        Student target = new Student("zhangsan", 23);

        // 如果 Student 没有重写 equals -> false（按地址）
        // 已重写 equals -> true（按内容）
        System.out.println("是否包含同名同龄学生: " + coll.contains(target));

        // remove 同理，也依赖 equals
        boolean removed = coll.remove(target);
        System.out.println("删除是否成功: " + removed + ", 剩余: " + coll);
    }
}
