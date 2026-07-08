package com.itheima.day14.finalkw;

/**
 * final 修饰"引用类型"的关键点：
 *
 *      final 基本类型：值不能改
 *      final 引用类型：地址值不能改，但对象内部的属性 / 数组的元素 仍然可以改
 *
 * 一句话总结：final 锁的是"变量本身"，不是"被指向对象的内容"。
 *
 * 拓展：
 *      想要"真不可变"，除了 final 还要让对象本身没有可写入口（如 String 的实现）。
 *      这就是"不可变对象（Immutable Object）"的思想。
 */
public class FinalReferenceDemo {
    public static void main(String[] args) {
        // 1) final 修饰对象引用
        final Student s = new Student("zhangsan", 23);
        // s = new Student();   // 编译失败：不能让 s 指向新的对象
        s.setName("李四");        // OK：对象内部的属性可以变
        s.setAge(24);
        System.out.println("s = " + s.getName() + ", " + s.getAge());

        // 2) final 修饰数组
        final int[] arr = {1, 2, 3, 4, 5};
        // arr = new int[10];   // 编译失败：不能让 arr 指向新的数组
        arr[0] = 10;             // OK：数组元素可以改
        arr[1] = 20;
        System.out.print("arr = ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + (i == arr.length - 1 ? "\n" : ", "));
        }

        // 3) 字符串不可变 —— String 本身是 final 类，且内部 char[] 是 private final
        final String str = "abc";
        String str2 = str.concat("def");   // 实际上返回了一个新的字符串
        System.out.println("str  = " + str  + " （原对象未变）");
        System.out.println("str2 = " + str2 + " （这是新对象）");
    }
}
