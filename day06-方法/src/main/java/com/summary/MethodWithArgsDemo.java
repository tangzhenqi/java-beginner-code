package com.summary;

/**
 * 知识点2：带参数的方法
 *
 *   格式：
 *     public static void 方法名(参数类型 参数名1, 参数类型 参数名2, ...) {
 *         方法体;
 *     }
 *
 *   调用：方法名(实参1, 实参2, ...);
 *
 *   关键术语：
 *     形式参数（形参）：方法定义时小括号中的参数，只是个"占位变量"。
 *     实际参数（实参）：方法调用时小括号中真正传入的值。
 *
 *   调用规则：
 *     1. 实参的"个数"和"顺序"必须与形参一致；
 *     2. 实参的类型必须与形参兼容（可以发生隐式类型转换，如 int → long）；
 *     3. 形参的作用域仅在方法内部。
 */
public class MethodWithArgsDemo {
    public static void main(String[] args) {
        // 传入不同实参，方法可以处理不同的数据
        printSum(10, 20);  // 30
        printSum(100, 200); // 300
        printSum(-5, 5);   // 0
    }

    public static void printSum(int num1, int num2) {
        int result = num1 + num2;
        System.out.println(result);
    }
}
