package com.summary;

/**
 * 知识点1：无限循环（死循环）的三种格式
 *
 *   for 写法：
 *     for (;;) { ... }
 *
 *   while 写法（最常用、最直观）：
 *     while (true) { ... }
 *
 *   do-while 写法：
 *     do { ... } while (true);
 *
 *   注意事项：
 *     1. 无限循环必须在循环体内通过 break 或 return 等方式跳出，否则程序卡死；
 *     2. 无限循环之后再写代码是不可达代码，编译器会直接报错；
 *     3. 实际开发中常用于服务器主循环、事件轮询等持续运行的场景。
 */
public class InfiniteLoopDemo {
    public static void main(String[] args) {
        // 演示一个会主动退出的无限循环：找到第一个 7 的倍数
        int i = 1;
        while (true) {
            if (i % 7 == 0) {
                System.out.println("找到了：" + i);
                break; // 跳出无限循环
            }
            i++;
        }

        // 错误示例（不可达代码，编译报错）：
        // while (true) {
        //     System.out.println("hi");
        // }
        // System.out.println("never reach"); // Unreachable statement
    }
}
