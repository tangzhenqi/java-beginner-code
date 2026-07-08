package com.itheima._01_generics;

/*
 * 知识点 4：泛型接口
 *
 * 接口中如果某些方法的参数/返回值类型在定义时尚不确定，就声明为泛型接口。
 * 实现接口有两种思路：
 *      (A) 实现类直接指定具体类型（见 StringMyListImpl）
 *      (B) 实现类继续保留泛型，把类型延迟到创建对象时再定（见 GenericMyListImpl）
 */
public interface MyList<E> {
    boolean add(E e);
    E get(int index);
    int size();
}
