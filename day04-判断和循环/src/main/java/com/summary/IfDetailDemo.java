package com.summary;

/**
 * 知识点2：if 语句的注意细节
 *
 *   1. 大括号的开头可以另起一行，但建议写在第一行的末尾。
 *   2. 如果语句体只有一句代码，大括号可以省略；不过为了可读性、避免后续修改出错，
 *      建议永远保留大括号。
 *   3. 如果是 boolean 变量判断，直接写变量名即可，不要用 == true。
 *      因为 (flag = true) 这种写法会变成赋值，编译能通过却产生逻辑错误。
 *   4. 小括号中的表达式结果必须是 boolean，所以 if (1) 在 Java 中是错的
 *      （这一点不同于 C/C++）。
 */
public class IfDetailDemo {
    public static void main(String[] args) {
        // 细节 2：单条语句省略大括号（不推荐，但合法）
        int number = 20;
        if (number >= 10)
            System.out.println("number 大于等于 10");

        // 细节 3：boolean 变量的判断写法
        boolean flag = true;
        if (flag) {
            System.out.println("flag 的值为 true");
        }

        // 反例（不要这样写）：
        // if (flag == true)  ——多此一举
        // if (flag = true)   ——这是赋值，永远为 true，是隐藏 bug
    }
}
