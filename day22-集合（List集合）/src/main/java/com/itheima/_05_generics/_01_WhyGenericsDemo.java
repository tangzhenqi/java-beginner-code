package com.itheima._05_generics;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 知识点十五：为什么需要泛型？
 *
 *  没有泛型时，集合元素都被当作 Object：
 *    - 任意类型都能放进去（编译期不约束）
 *    - 取出来必须强转，类型不匹配就 ClassCastException（运行期才炸）
 *
 *  泛型的好处：
 *    1. 把"类型检查"从运行期提前到编译期。
 *    2. 取数据时不用强转。
 *    3. 让 API 表达力更强（如 List<String> 一眼就知道存的是字符串）。
 *
 *  注意：泛型只在编译期存在，运行时会被"擦除"为 Object（见 _13_GenericErasureDemo）。
 */
public class _01_WhyGenericsDemo {
    public static void main(String[] args) {
        // —— 没有泛型 ——
        ArrayList rawList = new ArrayList();
        rawList.add("aaa");
        rawList.add(123);                 // 编译通过：什么类型都能放
        rawList.add(new Object());

        Iterator rawIt = rawList.iterator();
        while (rawIt.hasNext()) {
            Object obj = rawIt.next();    // 只能拿到 Object
            // String s = (String) obj;   // 万一不是 String 就 ClassCastException
            System.out.println(obj);
        }

        // —— 有了泛型 ——
        ArrayList<String> list = new ArrayList<>();
        list.add("aaa");
        // list.add(123);                 // ✅ 编译期直接报错，避免运行期出问题
        for (String s : list) {
            System.out.println(s.length()); // 不用强转，直接调 String 特有方法
        }
    }
}
