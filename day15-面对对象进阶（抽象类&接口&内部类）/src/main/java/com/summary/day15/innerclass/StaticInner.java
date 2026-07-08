package com.summary.day15.innerclass;

/**
 * 静态内部类：
 *  1. 也是成员内部类的一种，多了 static 修饰。
 *  2. 只能直接访问外部类的"静态"成员；要访问非静态成员需自己 new 外部类对象。
 *  3. 创建格式：外部类名.内部类名 对象 = new 外部类名.内部类名();
 *  4. 调用静态方法：外部类名.内部类名.方法名();
 *
 *  使用场景：与外部类逻辑相关但不依赖外部实例的工具/数据结构（如 Map.Entry）。
 */
public class StaticInner {

    public static void main(String[] args) {
        // 创建静态内部类对象（无须先创建外部类对象）
        Holder.Box box = new Holder.Box(42);
        box.show();

        // 调用静态内部类中的静态方法
        Holder.Box.staticMethod();
    }
}

class Holder {
    static int staticField = 100;
    int instanceField = 200;

    static class Box {
        int value;
        Box(int value) { this.value = value; }

        public void show() {
            System.out.println("Box value = " + value);
            System.out.println("外部静态字段 staticField = " + staticField);
            // System.out.println(instanceField);  // 编译错误：不能直接访问外部非静态成员
            // 必须 new 外部类对象才能访问
            System.out.println("通过 new Holder() 访问 instanceField = " + new Holder().instanceField);
        }

        public static void staticMethod() {
            System.out.println("静态内部类中的静态方法");
        }
    }
}
