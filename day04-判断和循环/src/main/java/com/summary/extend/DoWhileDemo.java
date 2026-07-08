package com.summary.extend;

/**
 * 拓展1：do-while 循环
 *
 *   格式：
 *     初始化语句;
 *     do {
 *         循环体;
 *         条件控制语句;
 *     } while (条件判断语句);
 *
 *   与 while 的区别：
 *     do-while "先执行，后判断"，因此循环体至少会执行一次。
 *
 *   适用场景：
 *     需要先做一次操作，再根据结果判断是否继续。例如菜单交互：先展示菜单，
 *     再询问用户是否退出。
 */
public class DoWhileDemo {
    public static void main(String[] args) {
        // 即使初始条件就为 false，也会先执行一次
        int i = 100;
        do {
            System.out.println("循环体至少执行一次：i = " + i);
            i++;
        } while (i < 10);

        // 经典菜单交互的伪代码：
        // int choice;
        // do {
        //     printMenu();
        //     choice = scanner.nextInt();
        //     handle(choice);
        // } while (choice != 0);
    }
}
