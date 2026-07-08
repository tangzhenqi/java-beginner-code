package com.summary.day15.abstractclass;

/**
 * 抽象类基础：
 * 1. 抽象方法：只有方法签名，没有方法体，用 abstract 修饰。
 * 2. 抽象类：含有抽象方法的类必须是抽象类（也可以没有抽象方法）。
 * 3. 抽象类不能直接 new，只能创建子类对象（多态）。
 * 4. 子类继承抽象类后：要么重写所有抽象方法，要么自己也声明 abstract。
 */
public class AbstractBasicsDemo {

    public static void main(String[] args) {
        // Shape s = new Shape();   // 编译错误：抽象类不能实例化

        Shape circle = new Circle("红色", 2.0);
        Shape rect   = new Rectangle("蓝色", 3.0, 4.0);

        // 多态调用：编译看左边 Shape，运行看右边的实际子类
        printShape(circle);
        printShape(rect);
    }

    private static void printShape(Shape s) {
        System.out.println(s.describe() + "，面积 = " + s.area());
    }
}

/** 抽象类：拥有共性属性 + 共性行为 + 必须被定义但无法在父层实现的抽象行为。 */
abstract class Shape {
    private final String color;

    // 抽象类是可以有构造方法的，作用是给成员变量初始化（被子类 super(...) 调用）
    protected Shape(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    /** 普通方法：所有形状的通用描述。 */
    public String describe() {
        return getClass().getSimpleName() + "(" + color + ")";
    }

    /** 抽象方法：无法在 Shape 层确定如何计算，必须延后到子类。 */
    public abstract double area();
}

class Circle extends Shape {
    private final double radius;

    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {
    private final double width;
    private final double height;

    public Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }
}
