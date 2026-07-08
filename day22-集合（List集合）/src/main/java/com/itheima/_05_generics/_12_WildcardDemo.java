package com.itheima._05_generics;

import com.itheima._05_generics._10_Family.Fu;
import com.itheima._05_generics._10_Family.Ye;
import com.itheima._05_generics._10_Family.Zi;

import java.util.ArrayList;

/**
 * 知识点二十：泛型通配符 ? 与上下界。
 *
 *  ?              代表"未知类型"，比 E 更宽松——只用于声明形参/字段，不能 new。
 *  ? extends T    上界通配符：可传 T 或 T 的子类。用于"只读取"（取出来当 T 用）。
 *  ? super T      下界通配符：可传 T 或 T 的父类。用于"只写入"（往里放 T 或 T 子类）。
 *
 *  口诀（PECS）：Producer Extends, Consumer Super。
 *    - 集合作为生产者（向外取数据）-> ? extends
 *    - 集合作为消费者（接收外部塞入的数据）-> ? super
 *
 *  使用场景：
 *    - 类型不确定时，方法签名想限定在"某个继承体系内"，用通配符。
 *    - 既不确定类型、又需要在方法体内 new 该类型 / 互相赋值，用 <E>（泛型方法）。
 */
public class _12_WildcardDemo {

    /** 只允许传 Fu 或 Fu 的子类（Fu/Zi）。Ye 不行。 */
    public static void acceptFuOrSub(ArrayList<? extends Fu> list) {
        // 可以读取，元素当作 Fu 用
        for (Fu f : list) {
            // ...
        }
        // 不能写入（除了 null），编译器无法保证类型安全
        // list.add(new Fu()); // 编译错误
    }

    /** 只允许传 Fu 或 Fu 的父类（Fu/Ye）。Zi 不行。 */
    public static void acceptFuOrSuper(ArrayList<? super Fu> list) {
        // 可以写入 Fu 或 Fu 的子类
        list.add(new Fu());
        list.add(new Zi());
        // 但读取时，只能当 Object 用
        Object o = list.get(0);
    }

    public static void main(String[] args) {
        ArrayList<Ye> ye = new ArrayList<>();
        ArrayList<Fu> fu = new ArrayList<>();
        ArrayList<Zi> zi = new ArrayList<>();

        // —— ? extends Fu ——
        // acceptFuOrSub(ye); // ❌ Ye 不是 Fu 的子类
        acceptFuOrSub(fu);
        acceptFuOrSub(zi);

        // —— ? super Fu ——
        acceptFuOrSuper(ye);
        acceptFuOrSuper(fu);
        // acceptFuOrSuper(zi); // ❌ Zi 不是 Fu 的父类

        System.out.println("ye=" + ye + ", fu=" + fu + ", zi=" + zi);
    }
}
