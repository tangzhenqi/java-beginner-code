package com.summary.day15.innerclass;

import java.util.ArrayList;
import java.util.List;

/**
 * 【什么时候该用静态内部类？】
 *
 * 一句话判断：
 *   内部类需要访问"外部类某个具体对象"的非静态字段吗？
 *     - 需要  → 用成员内部类（非静态）。它会隐式持有外部对象引用。
 *     - 不需要 → 用静态内部类。它就是个独立的类，只是"住"在外面，表达归属关系。
 *
 * 经验法则：默认先写 static。只有当编译器报错"必须访问外部实例字段"时，
 *          再去掉 static 改成成员内部类。这样能避免不必要的外部对象引用。
 *
 * 下面用"购物车 Cart 里有很多条目 Item"这个同一需求，对比两种写法。
 */
public class WhenToUse {

    public static void main(String[] args) {
        System.out.println("======== 推荐：静态内部类 ========");
        GoodCart cart = new GoodCart();
        cart.add("苹果", 3.5, 2);
        cart.add("牛奶", 9.9, 1);
        cart.printAll();
        System.out.println("总价 = " + cart.total());

        // Item 是纯数据，完全可以脱离某个具体 Cart 单独创建、单独传递
        GoodCart.Item single = new GoodCart.Item("赠品", 0, 1);
        System.out.println("独立创建一个 Item：" + single);

        System.out.println("\n======== 反例方向：迭代器 必须用 非静态内部类 ========");
        MyList list = new MyList("a", "b", "c");
        list.add("d");

        // 普通遍历
        MyList.Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println("遍历到：" + it.next() + "（剩余 " + it.remaining() + "）");
        }

        // 关键演示：同一个 list 上开两个独立迭代器，各走各的进度，互不干扰
        System.out.println("--- 两个独立迭代器同时遍历同一个 list ---");
        MyList.Iterator a = list.iterator();
        MyList.Iterator b = list.iterator();
        a.next();                 // a 先走一步
        System.out.println("a 接着拿：" + a.next());   // b
        System.out.println("b 从头拿：" + b.next());   // a —— 证明 b 有自己独立的进度
    }
}

/* =======================================================================
 * 写法 A（推荐）：Item 用【静态内部类】
 *
 * 为什么用 static？
 *   Item 只描述"商品名/单价/数量"这点自身数据，
 *   它压根不需要知道自己属于哪个 Cart，也不访问 Cart 的任何字段。
 *   → 它和 Cart 没有"实例级别"的依赖，只有"逻辑归属"。
 *   → 所以做成 static：更轻量、不持有外部引用、可独立创建。
 *
 * 这正是 JDK 里 Map.Entry、HashMap.Node、ArrayList 内部结构的设计方式。
 * ===================================================================== */
class GoodCart {
    private final List<Item> items = new ArrayList<>();

    /** 静态内部类：纯数据载体，不依赖任何 GoodCart 实例 */
    static class Item {
        final String name;
        final double price;
        final int count;

        Item(String name, double price, int count) {
            this.name = name;
            this.price = price;
            this.count = count;
        }

        double subtotal() { return price * count; }

        @Override
        public String toString() {
            return name + " x" + count + " = " + subtotal() + " 元";
        }
    }

    void add(String name, double price, int count) {
        items.add(new Item(name, price, count));
    }

    void printAll() {
        for (Item item : items) {
            System.out.println("  " + item);
        }
    }

    double total() {
        double sum = 0;
        for (Item item : items) {
            sum += item.subtotal();
        }
        return sum;
    }
}

/* =======================================================================
 * 写法 B（反例对照）：Item 用【成员内部类（非静态）】
 *
 * 问题在哪？
 *   1. 它隐式持有一个 BadCart 引用，即使根本用不到 —— 白白多占内存、
 *      还可能拖着 BadCart 不被 GC 回收（Android 里典型内存泄漏来源）。
 *   2. 无法脱离 BadCart 实例单独创建：
 *          new BadCart.Item(...)              // ❌ 编译错误
 *          new BadCart().new Item(...)        // 只能这样，啰嗦且别扭
 *   3. Item 本是"可以独立存在的数据"，强行绑定到某个 Cart 实例，
 *      语义和可读性都更差 —— 正是你之前吐槽的那种难读。
 *
 * 什么时候【确实该用】成员内部类？见 main 下方说明。
 * ===================================================================== */
class BadCart {
    private final List<Item> items = new ArrayList<>();

    /** 成员内部类：注意没有 static，于是它被迫绑定到一个 BadCart 实例 */
    class Item {
        final String name;
        final double price;
        final int count;

        Item(String name, double price, int count) {
            this.name = name;
            this.price = price;
            this.count = count;
        }

        double subtotal() { return price * count; }
    }

    void add(String name, double price, int count) {
        // 在外部类方法内部可以直接 new Item(...)，因为隐含了 BadCart.this
        items.add(new Item(name, price, count));
    }
    // 想在外部 new 一个 Item？只能写 new BadCart().new Item(...)，很别扭
}

/* =======================================================================
 * 反过来：什么时候【非静态成员内部类】才是对的？
 *
 * 当内部类的存在本身就"依附于某个外部对象"，且要访问它的非静态字段时。
 * 典型：迭代器 Iterator —— 它必须知道"正在遍历哪一个具体的列表实例"。
 *
 * 为什么迭代器是最经典的例子？
 *   1. 一个 MyList 可以同时有多个迭代器在遍历（比如嵌套循环），
 *      每个迭代器要各自记住"自己走到第几个了"(index)，
 *      但它们读的又是【同一个】MyList 实例的同一份 data。
 *   2. 迭代器离开了具体的列表对象就毫无意义 —— 它天生依附某个实例。
 *   这正是"实例级依赖"，所以必须用非静态成员内部类。
 *
 * 关键点：每个 new 出来的 Iterator 都隐式持有一个"外部 MyList 对象"的引用，
 *        所以它能直接写 data / size，等价于写 MyList.this.data。
 * ===================================================================== */
class MyList {
    private final String[] data;
    private int size;

    MyList(String... initial) {
        this.data = new String[16];
        for (String s : initial) {
            data[size++] = s;
        }
    }

    void add(String s) {
        data[size++] = s;
    }

    /**
     * 非静态成员内部类。
     * 注意：方法里直接写的 data / size，编译器自动补成 MyList.this.data / MyList.this.size，
     *      也就是"创建这个 Iterator 时所依附的那个 MyList 对象"的字段。
     */
    class Iterator {
        private int cursor = 0;   // 每个迭代器各自的进度，互不干扰

        boolean hasNext() {
            return cursor < size;          // 读外部实例的 size
        }

        String next() {
            return data[cursor++];         // 读外部实例的 data
        }

        /** 想显式写清楚依附的是哪个外部对象，可以用 MyList.this */
        int remaining() {
            return MyList.this.size - cursor;
        }
    }

    /** 每调用一次都 new 一个全新的、从头开始的迭代器 */
    Iterator iterator() {
        return new Iterator();
    }

    /* ---------------------------------------------------------------
     * 思考：能不能把 Iterator 改成 static class？—— 不能。
     *
     *   static class Iterator {
     *       boolean hasNext() { return cursor < size; }  // ❌ 编译错误
     *                                            ^^^^
     *       // 报错：无法从静态上下文引用非静态字段 size / data
     *   }
     *
     * 因为 static 内部类不持有任何 MyList 实例，自然不知道该读"哪个列表"的 data。
     * 若强行改 static，就只能把 MyList 当参数传进去，类似：
     *       static class Iterator {
     *           private final MyList list;       // 自己手动保存外部对象
     *           private int cursor = 0;
     *           Iterator(MyList list) { this.list = list; }
     *           boolean hasNext() { return cursor < list.size; }
     *       }
     * —— 这其实就是非静态内部类"自动帮你做"的事。所以这种场景直接用非静态最自然。
     * ------------------------------------------------------------- */
}
