package com.summary.extend;

/**
 * 拓展2：数组在内存中的结构（栈 vs 堆）
 *
 *   栈（Stack）：方法运行时分配，存放局部变量（包括数组变量本身——它只是一个"地址"）。
 *   堆（Heap）：new 出来的对象都在这里，数组的真实数据空间在堆中开辟。
 *
 *   关键结论：
 *     1. 数组变量保存的是堆中数据的"地址值"，所以 println(arr) 打印 [I@xxx；
 *     2. 多个数组变量可以指向同一个堆内存，修改其中一个会影响另一个；
 *     3. 当所有引用都消失时，堆内存的数据会被 GC 回收。
 */
public class ArrayMemoryDemo {
    public static void main(String[] args) {
        // arr1 和 arr2 各自指向自己堆中的数组
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {1, 2, 3};
        System.out.println(arr1 == arr2); // false，地址不同

        // arr3 与 arr1 指向同一块堆内存
        int[] arr3 = arr1;
        System.out.println(arr3 == arr1); // true
        arr3[0] = 999;
        System.out.println(arr1[0]); // 999，因为同一个数组

        // 让 arr1 不再指向，但 arr3 还在引用，堆中的数据暂时不会被回收
        arr1 = null;
        System.out.println(arr3[0]); // 999
    }
}
