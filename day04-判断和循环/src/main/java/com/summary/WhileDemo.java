package com.summary;

/**
 * 知识点6：while 循环
 *
 *   格式：
 *     初始化语句;
 *     while (条件判断语句) {
 *         循环体;
 *         条件控制语句;
 *     }
 *
 *   执行流程：
 *     先判断条件，true 则执行循环体，false 则结束循环。
 *
 *   适用场景：循环次数未知、需要根据条件动态决定何时退出。
 *
 *   for 与 while 对比：
 *     - 知道循环次数 → for（结构更紧凑）；
 *     - 不知道循环次数、根据条件循环 → while；
 *     - 二者完全可以互相改写，是等价的。
 */
public class WhileDemo {
    public static void main(String[] args) {
        // 利用 while 打印 1~10
        int i = 1;
        while (i <= 10) {
            System.out.println(i);
            i++;
        }

        // 典型场景：珠穆朗玛峰 8848.86 米，纸张厚度 0.1 毫米，对折多少次能超过山高？
        double mountain = 8848.86;
        double paper = 0.1 / 1000; // 转成米
        int count = 0;
        while (paper <= mountain) {
            paper *= 2;
            count++;
        }
        System.out.println("需要对折 " + count + " 次"); // 27
    }
}
