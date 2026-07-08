package com.summary;

/**
 * 知识点4：switch 的细节 —— default、case 穿透
 *
 *   default：
 *     1. 位置可以放在任意位置，不一定要写在最后（习惯写最下面）。
 *     2. 可以省略，但不建议省略，便于处理意外情况。
 *
 *   case 穿透：
 *     如果 case 后面忘了写 break，就会"穿透"——继续执行下一个 case 的语句体，
 *     一直到遇到 break 或 switch 的右大括号为止。
 *
 *     使用场景：多个 case 的语句体相同，可故意省略 break 简化代码。
 */
public class SwitchDetailDemo {
    public static void main(String[] args) {
        // 演示 case 穿透：判断月份属于哪个季节
        int month = 4;
        switch (month) {
            case 3:
            case 4:
            case 5:
                System.out.println("春季");
                break;
            case 6:
            case 7:
            case 8:
                System.out.println("夏季");
                break;
            case 9:
            case 10:
            case 11:
                System.out.println("秋季");
                break;
            case 12:
            case 1:
            case 2:
                System.out.println("冬季");
                break;
            default:
                System.out.println("月份非法");
                break;
        }

        // 演示忘写 break 的"事故现场"
        int number = 10;
        switch (number) {
            case 1:
                System.out.println("number 的值为 1");
                break;
            case 10:
                System.out.println("number 的值为 10");
                // 没写 break，会继续往下穿透
            case 20:
                System.out.println("number 的值为 20"); // 也会被打印
                break;
            default:
                System.out.println("number 不是 1、10 或 20");
                break;
        }
    }
}
