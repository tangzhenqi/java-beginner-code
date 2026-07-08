package com.summary;

/**
 * 知识点3：带返回值的方法
 *
 *   格式：
 *     public static 返回值类型 方法名(参数列表) {
 *         方法体;
 *         return 结果;
 *     }
 *
 *   说明：
 *     1. void 表示"没有返回值"，此时可以不写 return，或写空的 return;
 *     2. 有返回值的方法必须 return 一个与声明类型匹配的值；
 *     3. return 一旦执行，方法立即结束；
 *     4. 调用方式有三种：
 *        a) 直接调用：getSum(10, 20);                  // 不接收返回值
 *        b) 赋值调用：int s = getSum(10, 20);           // 接收返回值
 *        c) 输出调用：System.out.println(getSum(10, 20)); // 直接打印
 *
 *   有返回值方法的最大价值：能把"算出的结果"传回外部继续参与计算。
 */
public class MethodWithReturnDemo {
    public static void main(String[] args) {
        // 计算四个季度的营业额并求年度总和
        int sum1 = getSum(10, 20, 30);
        int sum2 = getSum(20, 30, 40);
        int sum3 = getSum(20, 30, 40);
        int sum4 = getSum(20, 30, 40);

        int year = sum1 + sum2 + sum3 + sum4;
        System.out.println("全年总营业额 = " + year);

        // 三种调用方式回顾
        getSum(1, 2, 3);                          // 直接调用，返回值被丢弃
        int s = getSum(1, 2, 3);                  // 赋值调用
        System.out.println(getSum(1, 2, 3) + s);  // 输出调用并参与运算
    }

    public static int getSum(int num1, int num2, int num3) {
        return num1 + num2 + num3; // 直接返回表达式的值
    }
}
