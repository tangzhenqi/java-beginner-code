package com.itheima._05_method_reference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * 方法引用 2：引用「对象」的成员方法
 * <p>
 * 格式：
 *   - 其他类对象： 对象::成员方法
 *   - 本类     ： this::成员方法   （引用处不能在静态方法中）
 *   - 父类     ： super::成员方法  （引用处不能在静态方法中）
 */
public class MRInstanceObjectDemo {

    // 演示本类 / 父类引用的场景需要在非静态方法里
    public void run() {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌", "周芷若", "赵敏", "张强", "张三丰");

        // 1. 其他类的对象
        StringOperation op = new StringOperation();
        ArrayList<String> upper = list.stream()
                .map(op::toUpper)
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(upper);

        // 2. 本类成员方法：this::
        // 等价于 .filter(s -> this.startsWithZhang(s))
        ArrayList<String> zhang = list.stream()
                .filter(this::startsWithZhang)
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(zhang);
    }

    /** 本类的成员方法，用作 this:: 的引用目标。 */
    private boolean startsWithZhang(String s) {
        return s.startsWith("张") && s.length() == 3;
    }

    public static void main(String[] args) {
        new MRInstanceObjectDemo().run();
    }
}
