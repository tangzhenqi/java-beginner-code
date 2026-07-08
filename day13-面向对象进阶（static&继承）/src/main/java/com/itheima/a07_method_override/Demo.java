package com.itheima.a07_method_override;

/**
 * 方法重写（Override）核心要点：
 *
 *  1. 方法签名必须一致：方法名 + 参数列表 完全相同。
 *  2. 返回值类型：相同，或为父类返回值的子类（协变返回）。
 *  3. 访问权限：不能比父类更小（如父类 public，子类只能 public）。
 *  4. 建议加 @Override 注解：编译器会校验是否真的重写成功，避免拼写错误。
 *
 *  应用场景：父类方法不能满足当前子类的需求时，对其重新实现。
 *  完全替换 -> 直接覆盖；只是补充 -> super.xxx() + 自己的扩展。
 */
public class Demo {
    public static void main(String[] args) {
        ChineseDog cd = new ChineseDog();
        cd.eat();
        cd.drink();      // 继承自 Dog，没重写
        cd.lookHome();   // 继承自 Dog

        System.out.println("--------");

        SharPei sp = new SharPei();
        sp.eat();         // super.eat() + 自己扩展
        sp.drink();
        sp.lookHome();
    }
}
