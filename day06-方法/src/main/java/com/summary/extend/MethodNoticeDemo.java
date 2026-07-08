package com.summary.extend;

/**
 * 拓展4：方法定义和使用的注意点
 *
 *   1. 方法不能嵌套定义 —— 一个方法的方法体里不能再写另一个方法定义；
 *   2. void 方法可以使用 return; 提前结束，但不能 return 值；
 *   3. 方法定义在 main 之前还是之后均可，编译器会找到它；
 *   4. main 方法本身也是方法，签名固定：public static void main(String[] args)；
 *   5. 方法名同名但参数列表相同 —— 编译报错（重复定义，不是重载）；
 *   6. 形参变量名不会影响调用方，实参可以是常量、变量或表达式。
 */
public class MethodNoticeDemo {
    public static void main(String[] args) {
        // void 方法的提前 return：年龄不合法时直接退出，不打印问候语
        greet(-1);
        greet(20);

        // 形参变量名可以与实参变量名同名，互不影响
        int x = 100;
        addOne(x);
        System.out.println("外部 x 仍是：" + x); // 100
    }

    public static void greet(int age) {
        if (age < 0) {
            System.out.println("年龄非法，跳过");
            return; // 提前结束 void 方法
        }
        System.out.println("你好，年龄是 " + age);
    }

    // 形参名也叫 x，但和 main 中的 x 完全是两个变量
    public static void addOne(int x) {
        x = x + 1;
    }
}
