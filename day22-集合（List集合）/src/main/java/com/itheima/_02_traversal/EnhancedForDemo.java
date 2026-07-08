package com.itheima._02_traversal;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 知识点六：Collection 通用遍历方式之 2 —— 增强 for（foreach 语法糖）。
 *
 *  格式：for (元素类型 变量名 : 集合或数组) { ... }
 *  IDEA 快捷生成：集合名.for + 回车
 *
 * 编译后等价于：
 *   for (Iterator<E> it = coll.iterator(); it.hasNext(); ) {
 *       E s = it.next();
 *       ...
 *   }
 *
 * 重要细节：
 *   循环变量 s 只是一个第三方"中转变量"，对它重新赋值
 *   不会回写到集合，因此下面打印的还是原始数据。
 */
public class EnhancedForDemo {
    public static void main(String[] args) {
        Collection<String> coll = new ArrayList<>();
        coll.add("zhangsan");
        coll.add("lisi");
        coll.add("wangwu");

        for (String s : coll) {
            s = "qqq"; // 仅改局部变量，不影响集合
        }

        System.out.println(coll); // [zhangsan, lisi, wangwu]
    }
}
