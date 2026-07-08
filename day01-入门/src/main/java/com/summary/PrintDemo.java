package com.summary;

/**
 * 知识点2：输出语句
 *
 *   System.out.println(内容);   打印后换行
 *   System.out.print(内容);     打印后不换行
 *   System.out.println();       仅换行（输出一个空行）
 *
 * 拆解：
 *   System    - JDK 提供的系统类
 *   out       - System 类中的标准输出流对象
 *   println   - 输出并换行的方法
 */
public class PrintDemo {
    public static void main(String[] args) {
        System.out.println("第一行");
        System.out.print("不换行A");
        System.out.print("不换行B");
        System.out.println();
        System.out.println("上面是空行后的新行");

        // 字符串拼接演示（拓展：+ 两侧任意一边是字符串则结果为字符串）
        System.out.println("作者：" + "黑马程序员");
    }
}
