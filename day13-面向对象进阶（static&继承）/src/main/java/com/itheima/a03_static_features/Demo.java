package com.itheima.a03_static_features;

/**
 * static 的访问特点 + 静态/实例代码块（拓展）。
 *
 * 访问规则：
 *  - 静态方法 -> 只能访问 静态成员变量、静态方法
 *  - 非静态方法 -> 既能访问静态，也能访问非静态
 *  - 静态方法中没有 this 关键字（因为 this 表示当前对象，静态方法属于类不属于对象）
 *
 * 拓展：代码块加载顺序（重要）
 *  父类静态代码块 -> 子类静态代码块（类加载阶段，只执行一次）
 *  父类实例代码块 -> 父类构造方法 -> 子类实例代码块 -> 子类构造方法（每次 new 时）
 *
 * 本例不涉及继承，仅观察"静态代码块只执行一次，实例代码块每次 new 都执行"。
 */
public class Demo {

    static int staticCounter;
    int instanceCounter;

    static {
        System.out.println("[1] 静态代码块：类首次加载时执行一次");
        staticCounter = 100;
    }

    {
        System.out.println("[2] 实例代码块：每次 new 都执行，在构造方法之前");
        instanceCounter++;
    }

    public Demo() {
        System.out.println("[3] 构造方法");
    }

    public static void main(String[] args) {
        System.out.println("==== main 开始 ====");
        System.out.println("staticCounter（类加载时已赋值）= " + staticCounter);

        System.out.println("---- new 第一个对象 ----");
        new Demo();
        System.out.println("---- new 第二个对象 ----");
        new Demo();

        // 静态方法只能访问静态成员
        staticOnly();
    }

    public static void staticOnly() {
        System.out.println("staticOnly: staticCounter=" + staticCounter);
        // System.out.println(instanceCounter); // 编译错误：静态方法不能访问实例成员
        // System.out.println(this);            // 编译错误：静态方法没有 this
    }
}
