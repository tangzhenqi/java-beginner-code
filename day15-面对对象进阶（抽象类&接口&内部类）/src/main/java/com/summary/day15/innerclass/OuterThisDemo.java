package com.summary.day15.innerclass;

/**
 * Outer.this：在成员内部类中获取外部类对象的引用。
 *
 *  当内部类成员、内部类方法局部变量、外部类成员同名时，要明确区分：
 *      局部变量   → 直接写名字
 *      内部类成员 → this.x
 *      外部类成员 → 外部类名.this.x
 */
public class OuterThisDemo {

    public static void main(String[] args) {
        Container.Item item = new Container().new Item();
        item.show();
    }
}

class Container {
    private int a = 10;

    class Item {
        private int a = 20;

        public void show() {
            int a = 30;
            System.out.println("局部变量            a = " + a);              // 30
            System.out.println("Item.this.a        = " + this.a);            // 20
            System.out.println("Container.this.a   = " + Container.this.a);  // 10
        }
    }
}
