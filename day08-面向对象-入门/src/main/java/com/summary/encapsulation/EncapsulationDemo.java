package com.summary.encapsulation;

/**
 * 演示封装的使用：
 *   - private 之后只能通过 setXxx 赋值、getXxx 读取。
 *   - 在 setter 中的校验能挡住"非法数据"。
 */
public class EncapsulationDemo {
    public static void main(String[] args) {
        GirlFriend gf = new GirlFriend();
        gf.setName("小诗诗");
        gf.setAge(18);          // 合法
        gf.setAge(150);         // 非法，被 setter 拦截，age 还是 18
        gf.setGender("女");

        System.out.println(gf.getName() + ", " + gf.getAge() + ", " + gf.getGender());

        // 下面这行会编译报错，因为 name 是 private：
        // gf.name = "黑客";
    }
}
