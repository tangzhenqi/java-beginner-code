package com.itheima.a07_method_override;

/**
 * 在父类实现之上"增强"：通过 super.xxx() 复用原有逻辑。
 */
public class SharPei extends Dog {

    @Override
    public void eat() {
        super.eat();
        System.out.println("沙皮狗还会啃骨头");
    }
}
