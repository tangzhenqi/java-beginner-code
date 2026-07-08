package com.summary.day15.innerclass;

/**
 * 成员内部类（非静态）：
 *  1. 写在类的成员位置，可被 private/默认/protected/public/static 修饰。
 *  2. 可以直接访问外部类的所有成员（包括 private）。
 *  3. JDK 16 之前不能定义静态变量；JDK 16 起允许定义静态变量。
 *  4. 创建方式：
 *     方式一：外部类提供工厂方法（适用于内部类是 private 的情况）
 *     方式二：Outer.Inner oi = new Outer().new Inner();
 */
public class MemberInner {

    public static void main(String[] args) {
        // 方式二：外部类对象 . new 内部类（）
        Car.Engine e = new Car("宾利").new Engine("V12");
        e.show();

        // 方式一：用 private 内部类时，只能让外部类对外提供
        Car car = new Car("法拉利");
        Object instance = car.createPrivateInstance();
        System.out.println("拿到 private 内部类对象：" + instance);
    }
}

class Car {
    private final String brand;

    public Car(String brand) {
        this.brand = brand;
    }

    /** 1) 公开的成员内部类 */
    public class Engine {
        String engineName;
        public Engine(String engineName) { this.engineName = engineName; }
        public void show() {
            // 内部类可以直接访问外部类私有成员
            System.out.println(brand + " 的发动机：" + engineName);
        }
    }

    /** 2) 私有的成员内部类，外界看不到，只能由外部类自己暴露对象。 */
    private class SecretBox {
        @Override
        public String toString() {
            return brand + ".SecretBox@" + Integer.toHexString(hashCode());
        }
    }

    public Object createPrivateInstance() {
        return new SecretBox();
    }
}
