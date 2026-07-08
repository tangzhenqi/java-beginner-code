package com.summary;

/**
 * 知识点5：for 循环
 *
 *   格式：
 *     for (初始化语句; 条件判断语句; 条件控制语句) {
 *         循环体;
 *     }
 *
 *   执行流程：
 *     1. 执行初始化语句（只执行一次）；
 *     2. 判断条件，true 则执行循环体，false 则结束循环；
 *     3. 执行条件控制语句；
 *     4. 回到第 2 步。
 *
 *   适用场景：循环次数已知。
 */
public class ForDemo {
    public static void main(String[] args) {
        // 打印 10 次 HelloWorld
        for (int i = 1; i <= 10; i++) {
            System.out.println("HelloWorld");
        }

        // 求 1~100 的和
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
        System.out.println("1~100 的和 = " + sum); // 5050

        // 注意：for 循环里定义的变量 i 是局部变量，作用域仅限于 for 内部
    }
}
