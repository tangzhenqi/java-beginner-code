package com.summary.clazz;

/**
 * 知识点 1：类的定义
 *
 * 一个类由两部分组成：
 *   - 属性（成员变量）：写在类里、方法外，描述这一类事物"有什么"。
 *   - 行为（成员方法）：描述这一类事物"能干什么"。
 *
 * 写法套路：
 *   public class 类名 {
 *       // 属性
 *       数据类型 属性名;
 *
 *       // 行为
 *       public 返回值类型 方法名(参数) { ... }
 *   }
 *
 * 注意：
 *   1. 一个 .java 文件里只能有一个 public 类，且类名必须和文件名一致。
 *   2. 成员变量如果不手动赋值，会有"默认值"：
 *      int -> 0,  double -> 0.0,  boolean -> false,  引用类型 -> null。
 */
public class Phone {
    // 属性（这里先不用 private，方便演示 new 对象后 . 赋值；正式写法应当 private，见 encapsulation 包）
    String brand;
    double price;

    // 行为
    public void call() {
        System.out.println(brand + " 正在打电话");
    }

    public void playGame() {
        System.out.println(brand + " 正在玩游戏");
    }
}
