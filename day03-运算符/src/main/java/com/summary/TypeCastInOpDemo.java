package com.summary;

/**
 * 知识点3：运算中的类型提升与强制转换
 *
 *   规则：byte / short / char 参与算术运算时会先自动提升为 int
 *   多类型混合：结果取较大的类型
 *      byte short char  ->  int  ->  long  ->  float  ->  double
 *
 *   强转：目标类型 变量 = (目标类型) 表达式;
 */
public class TypeCastInOpDemo {
    public static void main(String[] args) {
        byte b1 = 10;
        byte b2 = 20;
        // byte sum = b1 + b2;    // 编译报错：b1+b2 已经是 int
        byte sum = (byte) (b1 + b2);
        System.out.println(sum);  // 30

        // char 提升
        char c = 'a';
        int ascii = c + 0;
        System.out.println(ascii); // 97

        // int + double = double
        int i = 10;
        double d = 1.5;
        System.out.println(i + d); // 11.5
    }
}
