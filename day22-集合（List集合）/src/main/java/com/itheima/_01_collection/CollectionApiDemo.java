package com.itheima._01_collection;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 知识点一：Collection 接口的共性方法。
 *
 *  public boolean add(E e)             添加
 *  public void    clear()              清空
 *  public boolean remove(E e)          删除（按元素，非索引）
 *  public boolean contains(Object obj) 判断是否包含（底层依赖 equals）
 *  public boolean isEmpty()            判断是否为空
 *  public int     size()               集合长度
 *
 * 细节：
 *  1. Collection 是接口，不能 new，只能通过实现类（ArrayList/HashSet 等）创建对象。
 *  2. add 在 List 系列永远返回 true（List 允许重复）；
 *     在 Set 系列：元素不存在返回 true，已存在返回 false（Set 不允许重复）。
 *  3. remove 只能按"元素"删除，没有"按索引删除"的共性方法。
 *  4. contains 底层用 equals 判等，自定义对象必须重写 equals。
 */
public class CollectionApiDemo {
    public static void main(String[] args) {
        // 泛型说明：
        //   new ArrayList<>() 中的 <> 是"钻石语法"，不是不指定泛型，
        //   而是让编译器根据左边的 Collection<String> 推断出 String，等价于 new ArrayList<String>()。
        //   只有完全不写 <>（即原始类型，如 new ArrayList()）时，元素才默认按 Object 处理，
        //   但那样会丢失类型检查、出现 unchecked 警告，不推荐。
        Collection<String> coll = new ArrayList<>();

        // 1.添加
        coll.add("aaa");
        coll.add("bbb");
        coll.add("ccc");
        System.out.println("添加后: " + coll);

        // 2.删除（按元素）。返回值表示是否删除成功
        boolean removed = coll.remove("aaa");
        System.out.println("删除 aaa 是否成功: " + removed + ", 集合: " + coll);

        // 3.判断包含
        System.out.println("是否包含 bbb: " + coll.contains("bbb"));

        // 4.判断是否为空
        System.out.println("是否为空: " + coll.isEmpty());

        // 5.长度
        coll.add("ddd");
        System.out.println("当前长度: " + coll.size());

        // 6.清空
        coll.clear();
        System.out.println("清空后: " + coll + ", 是否为空: " + coll.isEmpty());
    }
}
