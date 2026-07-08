package com.itheima.day14.polymorphism;

/**
 * 多态的弊端 + 向下转型：
 *     父类引用看不到子类的特有方法，
 *     如果一定要调用，需要把它"变回"子类类型。
 *
 * 安全做法：先 instanceof 判断再强转，避免 ClassCastException。
 * 现代写法：instanceof 的"模式匹配"语法 —— 判断 + 强转 + 声明变量 一气呵成。
 */
public class PolymorphismCastDemo {
    public static void main(String[] args) {
        // 1) 父类引用调用被子类重写的方法 —— 运行的是子类版本
        Animal a = new Dog(2, "黑");
        a.eat("骨头");

        // 2) 父类引用不能直接调用子类特有方法 —— 编译失败
        // a.lookHome();  // 编译错误：Animal 中没有 lookHome

        // 3) 老写法：先 instanceof 判断，再强转
        if (a instanceof Dog) {
            Dog d = (Dog) a;
            d.lookHome();
        }

        // 4) 新写法：instanceof 模式匹配
        if (a instanceof Dog d) {
            d.lookHome();
        } else if (a instanceof Cat c) {
            c.catchMouse();
        }

        // 5) 错误的向下转型会抛 ClassCastException —— 演示如何捕获
        try {
            Cat wrong = (Cat) a;     // a 实际是 Dog，强转成 Cat 必然失败
            wrong.catchMouse();
        } catch (ClassCastException e) {
            System.out.println("捕获到类型转换异常：" + e.getMessage());
        }
    }
}
