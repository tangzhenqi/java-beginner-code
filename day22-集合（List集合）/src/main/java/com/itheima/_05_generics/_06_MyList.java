package com.itheima._05_generics;

/**
 * 知识点十八：泛型接口。
 *
 *  接口定义时声明类型参数 <E>，实现类有两种处理方式：
 *      ① 实现时"写死"具体类型（_07_StringList implements _06_MyList<String>）
 *      ② 实现类继续保留泛型（_08_AnyList<E> implements _06_MyList<E>），
 *         由真正 new 对象的人来指定。
 */
public interface _06_MyList<E> {
    boolean add(E e);
    E get(int index);
    int size();
}
