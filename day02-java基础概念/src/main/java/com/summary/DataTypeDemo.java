package com.summary;

/**
 * 知识点3：Java 的 8 种基本数据类型
 *
 *   类别     类型        字节   取值范围
 *   整数     byte        1      -128 ~ 127
 *            short       2      -2^15 ~ 2^15-1
 *            int (默认)   4      -2^31 ~ 2^31-1
 *            long        8      -2^63 ~ 2^63-1     字面量加 L
 *   小数     float       4      精度约 7 位         字面量加 F
 *            double(默认) 8      精度约 15 位
 *   字符     char        2      0 ~ 65535（Unicode）
 *   布尔     boolean     1      true / false
 *
 * 引用数据类型：String、数组、类、接口... 首字母均大写
 */
public class DataTypeDemo {
    public static void main(String[] args) {
        byte b = 100;
        short s = 30000;
        int i = 100000;
        long l = 10000000000L;       // 超出 int 范围必须加 L

        float f = 3.14F;             // 不加 F 默认是 double，赋值给 float 会报错
        double d = 3.1415926;

        char c = 'A';
        boolean flag = true;

        System.out.println(b);
        System.out.println(s);
        System.out.println(i);
        System.out.println(l);
        System.out.println(f);
        System.out.println(d);
        System.out.println(c);
        System.out.println(flag);
    }
}
