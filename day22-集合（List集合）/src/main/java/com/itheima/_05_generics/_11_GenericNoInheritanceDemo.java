package com.itheima._05_generics;

import com.itheima._05_generics._10_Family.Fu;
import com.itheima._05_generics._10_Family.Ye;
import com.itheima._05_generics._10_Family.Zi;

import java.util.ArrayList;

/**
 * 知识点十九：泛型本身没有继承性，但"数据"具备继承性。
 *
 *  - ArrayList<Fu> 不是 ArrayList<Ye> 的子类，即使 Fu 是 Ye 的子类。
 *    所以接受形参类型 ArrayList<Ye> 的方法，不能传 ArrayList<Fu>。
 *
 *  - 但同一个 ArrayList<Ye> 里，可以存 Ye/Fu/Zi 的实例（这就是数据的多态）。
 */
public class _11_GenericNoInheritanceDemo {
    public static void main(String[] args) {
        ArrayList<Ye> list1 = new ArrayList<>();
        ArrayList<Fu> list2 = new ArrayList<>();
        ArrayList<Zi> list3 = new ArrayList<>();

        // 数据具备继承性：Ye 类型集合可以装 Fu、Zi
        list1.add(new Ye());
        list1.add(new Fu());
        list1.add(new Zi());

        // 但集合"本身"没有继承性：下面两行都编译不过
        // onlyYeList(list2);
        // onlyYeList(list3);
        onlyYeList(list1);
    }

    /** 形参写死 ArrayList<Ye>，那就只能传 ArrayList<Ye>，不能传子类版本。 */
    public static void onlyYeList(ArrayList<Ye> list) {
        System.out.println("收到一个 ArrayList<Ye>，长度=" + list.size());
    }
}
