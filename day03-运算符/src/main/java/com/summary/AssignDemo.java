package com.summary;

/**
 * 知识点4：赋值运算符  =  +=  -=  *=  /=  %=
 *
 *   规则：将左右两边运算后再赋给左边
 *
 *   重要细节：
 *     +=  -=  *=  /=  %=  底层隐藏了一个强制类型转换
 *     short s = 1;
 *     s += 1;        // 合法：等价于 s = (short)(s + 1)
 *     // s = s + 1;  // 编译报错：s+1 已经是 int
 */
public class AssignDemo {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        a += b;          // a = a + b
        System.out.println(a);   // 30

        // 隐含强转
        short s = 1;
        s += 1;
        System.out.println(s);   // 2

        // 其他复合赋值
        int n = 100;
        n -= 10;  System.out.println(n);  // 90
        n *= 2;   System.out.println(n);  // 180
        n /= 3;   System.out.println(n);  // 60
        n %= 7;   System.out.println(n);  // 4
    }
}
