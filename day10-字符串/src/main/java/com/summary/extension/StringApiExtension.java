package com.summary.extension;

/**
 * 拓展：String 还有一批"特别常用、教材没讲全"的方法。
 *
 *   trim()                 去掉首尾空白（不能去掉中间）
 *   strip()      JDK11+    比 trim 更强，能去 Unicode 空白
 *   isEmpty()              长度是否为 0
 *   isBlank()    JDK11+    长度为 0 或全是空白
 *   contains(s)            是否包含子串
 *   startsWith(s)          是否以 s 开头
 *   endsWith(s)            是否以 s 结尾
 *   indexOf(s)             第一次出现的位置，不存在返回 -1
 *   split(regex)           按正则切分成数组
 *   String.format(fmt,...) 格式化得到一个字符串
 *   repeat(n)    JDK11+    把自己重复 n 次
 *   String.join(...)       用分隔符把若干字符串拼起来
 */
public class StringApiExtension {
    public static void main(String[] args) {
        String s = "   Hello, World!   ";

        System.out.println("[" + s.trim() + "]");           // [Hello, World!]
        System.out.println(s.contains("World"));            // true
        System.out.println(s.trim().startsWith("Hello"));   // true
        System.out.println(s.indexOf("World"));             // 索引位置

        // split：按逗号切分
        String csv = "a,b,c,d";
        String[] parts = csv.split(",");
        for (String p : parts) {
            System.out.print(p + " ");
        }
        System.out.println();

        // String.join：和 split 互为逆操作
        String joined = String.join("-", parts);
        System.out.println(joined);   // a-b-c-d

        // format
        String line = String.format("姓名:%s 年龄:%d 薪资:%.2f", "张三", 28, 12345.6789);
        System.out.println(line);
    }
}
