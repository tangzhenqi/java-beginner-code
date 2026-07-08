package com.summary;

/**
 * 知识点2：循环的跳转 —— break 与 continue
 *
 *   continue：结束本次循环，进入下一次循环（"跳过这一次"）。
 *   break   ：结束整个循环（"提前下车"）。
 *
 *   注意：
 *     1. 二者只能用在循环中（break 也可用在 switch 中）；
 *     2. 在嵌套循环里，默认只控制最内层循环；
 *     3. 想要一次跳出多层，可以配合"带标签的循环"（见 extend/LabeledLoopDemo）。
 */
public class JumpDemo {
    public static void main(String[] args) {
        // continue：跳过第 3 个包子
        for (int i = 1; i <= 5; i++) {
            if (i == 3) {
                continue;
            }
            System.out.println("小老虎在吃第 " + i + " 个包子");
        }

        System.out.println("---");

        // break：吃到第 3 个就饱了
        for (int i = 1; i <= 5; i++) {
            System.out.println("小老虎在吃第 " + i + " 个包子");
            if (i == 3) {
                break;
            }
        }
    }
}
