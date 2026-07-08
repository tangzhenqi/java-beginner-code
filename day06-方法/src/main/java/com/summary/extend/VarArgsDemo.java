package com.summary.extend;

/**
 * 拓展5：可变参数（Varargs，JDK 5 引入）
 *
 *   格式：
 *     public static 返回值类型 方法名(类型... 参数名) { ... }
 *
 *   要点：
 *     1. 在方法内部，可变参数实际上就是一个数组；
 *     2. 调用时可以传 0 个、1 个、多个，也可以直接传一个数组；
 *     3. 一个方法只能有一个可变参数，并且必须放在形参列表的最后；
 *     4. 重载时编译器优先匹配"精确签名"，最后才考虑可变参数版本。
 *
 *   典型应用：String.format、Arrays.asList、各种"取最值/求和"工具方法。
 */
public class VarArgsDemo {
    public static void main(String[] args) {
        System.out.println(sum());          // 0
        System.out.println(sum(1));         // 1
        System.out.println(sum(1, 2, 3));   // 6
        System.out.println(sum(new int[]{10, 20, 30})); // 60，直接传数组也可以
    }

    public static int sum(int... nums) {
        int s = 0;
        for (int n : nums) { // nums 在方法内是一个 int[]
            s += n;
        }
        return s;
    }
}
