package com.summary;

/**
 * 知识点4：关键字 与 标识符
 *
 * 一、关键字
 *   被 Java 赋予特殊含义的英文单词，全部小写。
 *   常见：public  class  static  void  if  else  for  while  return
 *        int  long  double  boolean  true  false  null  new  this  super  final
 *
 * 二、标识符（给类、方法、变量起的名字）
 *   硬性规则：
 *     1. 由字母、数字、下划线 _、美元符 $ 组成
 *     2. 不能以数字开头
 *     3. 不能使用 Java 关键字
 *     4. 区分大小写
 *   命名规范：
 *     - 类名     大驼峰      HelloWorld、StudentService
 *     - 方法/变量 小驼峰      userName、getAge
 *     - 常量     全大写_分隔  MAX_VALUE、PI
 *     - 包名     全小写       com.itheima.demo
 *   原则：见名知意。
 */
public class KeywordIdentifierDemo {
    public static void main(String[] args) {
        // 正确示范
        String userName = "张三";       // 小驼峰
        int studentAge = 18;
        final double PI = 3.1415;      // 常量

        System.out.println(userName + " " + studentAge + " " + PI);

        // 错误示范（仅用注释展示，不实际写）
        // int 1name = 1;     // 错：数字开头
        // int class  = 1;    // 错：使用关键字
        // int user-name = 1; // 错：含非法字符 -
    }
}
