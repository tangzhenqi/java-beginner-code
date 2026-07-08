package com.summary.extend;

/**
 * 拓展2：方法参数的传递 —— Java 永远是"值传递"
 *
 *   规则：
 *     1. 基本数据类型作为参数：传递的是"值的拷贝"，方法内修改不影响外部；
 *     2. 引用数据类型作为参数：传递的是"地址值的拷贝"，但两个变量指向同一个堆对象，
 *        方法内通过该引用修改对象内部状态，外部能看到变化；
 *        但是，如果在方法内"重新赋值"参数（让它指向别的对象），并不会影响外部。
 *
 *   一句话总结：Java 中没有真正的"引用传递"，只有值传递 —— 只不过传的可能是地址值。
 */
public class ParamPassDemo {
    public static void main(String[] args) {
        // 1. 基本类型
        int num = 10;
        changeBasic(num);
        System.out.println("基本类型外部：" + num); // 10，没变

        // 2. 引用类型 —— 通过引用修改数组内容
        int[] arr = {1, 2, 3};
        changeArray(arr);
        System.out.println("数组第一项：" + arr[0]); // 999，被改了

        // 3. 引用类型 —— 方法内重新赋值，不影响外部
        int[] arr2 = {1, 2, 3};
        reassignArray(arr2);
        System.out.println("数组第一项（重新赋值场景）：" + arr2[0]); // 1，仍是原数组
    }

    public static void changeBasic(int n) {
        n = 999; // 只修改了局部变量的拷贝
    }

    public static void changeArray(int[] a) {
        a[0] = 999; // 通过共享的地址修改了堆中的数据
    }

    public static void reassignArray(int[] a) {
        a = new int[]{777, 888, 999}; // 让形参指向新数组，但外部变量不受影响
    }
}
