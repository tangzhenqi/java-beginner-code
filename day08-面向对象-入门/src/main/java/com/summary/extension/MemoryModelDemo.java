package com.summary.extension;

/**
 * 拓展 1：对象在内存中的样子（极简版）
 *
 *   栈（stack）：方法里的"局部变量"，包括"对象的引用"。
 *   堆（heap）：通过 new 出来的"对象本体"，存属性值。
 *
 *   Phone p = new Phone();
 *        ↑              ↑
 *      在栈           在堆
 *
 *   p 里"存的不是对象本身，而是堆中对象的地址"。
 *   所以下面 a 和 b 指向同一个对象，改 a 的属性，b 也会变：
 */
class Box {
    int value;
}

public class MemoryModelDemo {
    public static void main(String[] args) {
        Box a = new Box();
        a.value = 10;

        Box b = a;              // 把"地址"赋给 b，不是复制对象！
        b.value = 99;

        System.out.println(a.value);   // 99，被 b 改了
        System.out.println(b.value);   // 99

        // 再 new 一个，才是真正"另一个对象"
        Box c = new Box();
        c.value = a.value;             // 这只是把"值"拷过去
        a.value = 1;
        System.out.println(c.value);   // 99，不受 a 影响
    }
}
