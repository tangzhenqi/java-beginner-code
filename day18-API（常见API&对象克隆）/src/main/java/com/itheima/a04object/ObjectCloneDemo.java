package com.itheima.a04object;

/**
 * 知识点四：Object —— clone（浅克隆 vs 深克隆）
 * <p>
 * 使用步骤：
 *   1. JavaBean 类 implements Cloneable
 *   2. 重写 Object.clone()，把 protected 提升为 public
 *   3. 创建原对象 → 调用 clone()
 * <p>
 * 浅克隆：基本类型字段复制值，引用类型字段共享地址。
 * 深克隆：手动把引用字段也复制一份，做到完全独立。
 * <p>
 * 实际开发中常用第三方工具（Gson、Hutool、Apache Commons Lang）
 * 通过"序列化-反序列化"实现深克隆，更通用。
 */
public class ObjectCloneDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        int[] data = {1, 2, 3, 4, 5};
        User u1 = new User(1, "zhangsan", "1234", data);
        User u2 = (User) u1.clone();

        // 修改原对象的数组，看是否影响克隆体
        u1.getData()[0] = 999;

        System.out.println("原对象  ：" + u1);
        System.out.println("克隆对象：" + u2);

        // 因为重写的 clone 中复制了一份新数组，所以 u2.data 不受影响 —— 深克隆。
        // 如果只调用 super.clone() 不处理数组，u2.data[0] 也会变成 999 —— 浅克隆。
    }
}
