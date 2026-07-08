package com.itheima._01_generics;

import java.util.ArrayList;

/*
 * 知识点 5：泛型的继承性 & 通配符
 *
 * 一、泛型不具备继承性
 *      虽然 Integer extends Number 成立，
 *      但 ArrayList<Integer> 并不是 ArrayList<Number> 的子类。
 *      因此下面这行编译错误：
 *          ArrayList<Number> list = new ArrayList<Integer>();
 *
 * 二、但是"数据本身"具备继承性
 *      ArrayList<Number> list = new ArrayList<>();
 *      list.add(123);          // OK，Integer 是 Number 的子类
 *      list.add(3.14);         // OK，Double 也是 Number 的子类
 *
 * 三、解决"方法形参泛型类型如何写"的问题 —— 通配符 ?
 *      ?                  : 不确定的类型（无界）
 *      ? extends E        : E 或 E 的子类（上界，常用于"只读取"）
 *      ? super E          : E 或 E 的父类（下界，常用于"只写入"）
 *
 *      经典口诀：PECS —— Producer Extends, Consumer Super
 *          想"取出"用 ? extends T
 *          想"放入"用 ? super T
 *
 * 四、什么时候用泛型、什么时候用通配符？
 *      - 类型完全自由 → 泛型 <E>
 *      - 类型自由但限定在某继承体系中 → 通配符 ? extends X / ? super X
 */
public class _05_Wildcard {
    public static void main(String[] args) {
        ArrayList<Ye>  list1 = new ArrayList<>();
        ArrayList<Fu>  list2 = new ArrayList<>();
        ArrayList<Zi>  list3 = new ArrayList<>();
        ArrayList<Other> list4 = new ArrayList<>();

        // method1 只接受 ArrayList<Ye>，list2/list3 都不行
        method1(list1);
        // method1(list2);   // 编译错误：泛型不具备继承性

        // method2 用 ? extends Fu：能传 Fu、Zi，不能传 Ye（父类不行）
        // method2(list1);   // 编译错误
        method2(list2);
        method2(list3);

        // method3 用 ? super Fu：能传 Fu、Ye，不能传 Zi（子类不行）
        method3(list1);
        method3(list2);
        // method3(list3);   // 编译错误

        // method4 用 ? 完全不限定（但只能"读"成 Object，不能"加"具体类型）
        method4(list4);
    }

    public static void method1(ArrayList<Ye> list) {}

    public static void method2(ArrayList<? extends Fu> list) {
        // list.add(new Fu());   // 编译错误：不能往"上界通配符"的集合里加元素（null 除外）
        // 原因：list 实际可能是 ArrayList<Zi>，加 Fu 会破坏类型安全
        for (Fu f : list) {       // 只读没问题：取出来一定是 Fu 或其子类
            System.out.println(f);
        }
    }

    public static void method3(ArrayList<? super Fu> list) {
        list.add(new Fu());       // 可以加 Fu 或 Fu 的子类
        list.add(new Zi());
        // 但取出来只能用 Object 接，因为不知道具体上界
    }

    public static void method4(ArrayList<?> list) {
        // 读：只能当成 Object
        for (Object o : list) {
            System.out.println(o);
        }
        // 写：除了 null 啥也不能加
        // list.add(new Object());  // 编译错误
    }
}

class Ye {                  // 爷爷
    @Override public String toString() { return "Ye"; }
}
class Fu extends Ye {       // 父亲
    @Override public String toString() { return "Fu"; }
}
class Zi extends Fu {       // 儿子
    @Override public String toString() { return "Zi"; }
}
class Other {}              // 与 Ye-Fu-Zi 体系无关
