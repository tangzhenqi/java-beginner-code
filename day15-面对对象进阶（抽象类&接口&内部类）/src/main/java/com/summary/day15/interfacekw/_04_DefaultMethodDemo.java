package com.summary.day15.interfacekw;

/**
 * 接口默认方法（JDK 8 引入）：
 *  格式：public default 返回值 方法名(...) { ... }
 *  注意：
 *      1. 默认方法不是抽象方法，实现类可以选择"不重写"。
 *      2. 实现类如果重写默认方法，重写时要去掉 default 关键字。
 *      3. 多实现冲突：如果实现了多个接口，且接口中有同名 default 方法，
 *         实现类必须强制重写以消除歧义。
 *  设计意图：
 *      JDK 集合框架在 JDK 8 加入 forEach 等方法时，老代码已经实现了 Iterable/Collection，
 *      用 default 方法 可以"加新方法但不破坏二进制兼容"。
 */
public class _04_DefaultMethodDemo {

    public static void main(String[] args) {
        ServiceImpl impl = new ServiceImpl();
        impl.coreApi();
        impl.commonApi();    // 用的是 ServiceA 中的默认实现

        // 多实现冲突 → 必须重写
        ConflictImpl c = new ConflictImpl();
        c.show();
    }
}

interface ServiceA {
    void coreApi();                            // 抽象方法：必须实现

    default void commonApi() {                 // 默认方法：可不重写
        System.out.println("ServiceA 提供的通用实现");
    }
}

class ServiceImpl implements ServiceA {
    @Override
    public void coreApi() {
        System.out.println("ServiceImpl 的核心实现");
    }
    // 不重写 commonApi，使用接口默认实现
}

/* ---- 多实现冲突示例 ---- */
interface ConflictA {
    default void show() { System.out.println("ConflictA.show"); }
}
interface ConflictB {
    default void show() { System.out.println("ConflictB.show"); }
}
class ConflictImpl implements ConflictA, ConflictB {
    /** 不重写 show 编译都通不过；重写时把 default 去掉。 */
    @Override
    public void show() {
        // 也可以显式调用某一边：ConflictA.super.show();
        ConflictA.super.show();
        ConflictB.super.show();
        System.out.println("ConflictImpl 自己的实现");
    }
}
