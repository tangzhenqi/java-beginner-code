package com.summary.day15.interfacekw;

/**
 * 接口的成员规则：
 *  成员变量：只能是常量，默认修饰符 public static final
 *  构造方法：没有
 *  成员方法：JDK 7 及之前只能是抽象方法，默认 public abstract
 *           JDK 8 起还可以有 default / static 方法
 *           JDK 9 起还可以有 private 方法
 */
public class _02_InterfaceMembersDemo {

    public static void main(String[] args) {
        // 1. 常量直接用 接口名.常量名 访问
        System.out.println("PI = " + Constants.PI);
        System.out.println("MAX = " + Constants.MAX);
        // Constants.PI = 4;  // 编译错误：被隐式 final 修饰

        // 2. 接口不能 new
        // Constants c = new Constants();  // 编译错误

        // 3. 通过实现类对象调用抽象方法
        Constants impl = new ConstantsImpl();
        impl.method();
    }
}

interface Constants {
    // 等价于 public static final double PI = 3.14;
    double PI  = 3.14;
    int    MAX = 100;

    // 等价于 public abstract void method();
    void method();
}

class ConstantsImpl implements Constants {
    @Override
    public void method() {
        System.out.println("实现类重写的抽象方法");
    }
}
