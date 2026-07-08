package com.summary.clazz;

/**
 * 使用一个类的三步走：
 *
 *   1. 创建对象：       类名 对象名 = new 类名();
 *   2. 给属性赋值：     对象名.属性名 = 值;
 *      或者读属性：     System.out.println(对象名.属性名);
 *   3. 调方法：         对象名.方法名();
 */
public class PhoneDemo {
    public static void main(String[] args) {
        // 1. 创建一个手机对象
        Phone p = new Phone();

        // 2. 赋值
        p.brand = "小米";
        p.price = 1999.98;

        // 读属性
        System.out.println("品牌：" + p.brand);
        System.out.println("价格：" + p.price);

        // 3. 调方法
        p.call();
        p.playGame();
    }
}
