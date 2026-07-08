package com.summary;

/**
 * 知识点7：三元运算符
 *
 *   格式：  关系表达式 ? 表达式1 : 表达式2 ;
 *   规则：  关系表达式为 true 取 表达式1，否则取 表达式2
 *   要求：  整个三元表达式的结果必须被使用（赋值/打印/作为参数等）
 */
public class TernaryDemo {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;

        // 1) 赋值后使用
        int max = a > b ? a : b;
        System.out.println("最大值：" + max);

        // 2) 直接作为打印参数
        System.out.println("较小值：" + (a < b ? a : b));

        // 3) 嵌套三元 - 找三个数最大值
        int c = 15;
        int max3 = a > b ? (a > c ? a : c) : (b > c ? b : c);
        System.out.println("三数最大：" + max3);
    }
}
