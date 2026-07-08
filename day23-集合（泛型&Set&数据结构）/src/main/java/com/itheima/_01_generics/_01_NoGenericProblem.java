package com.itheima._01_generics;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * 知识点 1：为什么要有泛型
 *
 * 没有泛型时：
 *      集合默认把所有数据当成 Object 类型存储。
 *      优点：什么都能塞进去。
 *      缺点：取出来必须强转，并且运行时才会暴露 ClassCastException。
 *
 * 有了泛型后：
 *      把"类型检查"从运行期提前到编译期，
 *      取出元素不再需要强转，可以直接使用该类型的特有行为。
 *
 * 泛型本质：
 *      "类型擦除"。源码里写的 <String>，编译后字节码中的类型还是 Object，
 *      只是编译器在编译期帮你做了类型检查和自动强转。
 */
public class _01_NoGenericProblem {
    public static void main(String[] args) {
        // ===== 无泛型版（不推荐）=====
        ArrayList list = new ArrayList();
        list.add("hello");
        list.add(123);                  // 编译期不会报错
        list.add(new Object());

        // 遍历时必须强转，类型不一致就会 ClassCastException
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            // String s = (String) obj; // 第二个元素 Integer 会抛出异常
            System.out.println(obj);
        }

        System.out.println("--------------");

        // ===== 有泛型版（推荐）=====
        ArrayList<String> strList = new ArrayList<>();
        strList.add("aaa");
        // strList.add(123);            // 编译期就会报错，类型不安全的代码无法通过编译

        Iterator<String> it2 = strList.iterator();
        while (it2.hasNext()) {
            String s = it2.next();      // 无需强转，直接得到 String
            System.out.println(s.length()); // 可直接调用 String 的特有方法
        }
    }
}
