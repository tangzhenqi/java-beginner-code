package com.summary.day15.innerclass;

/**
 * 局部内部类：
 *  1. 定义在方法、构造方法、代码块内部，作用域仅限所在方法。
 *  2. 外界无法直接使用，只能在所在方法里 new 出来用。
 *  3. 可以访问外部类的所有成员，也可以访问所在方法的局部变量
 *     （这些局部变量必须是 final 或事实 final）。
 *
 *  实际开发中较少直接写局部内部类，更多是用匿名内部类 / lambda 代替。
 */
public class LocalInner {

    public static void main(String[] args) {
        new Wrapper().run();
    }
}

class Wrapper {
    int outerField = 200;

    public void run() {
        int localVar = 100;          // 事实 final（赋值后未再修改）

        // 局部内部类
        class Worker {
            String name = "Worker-1";

            public void doWork() {
                System.out.println("局部变量 localVar  = " + localVar);
                System.out.println("外部成员 outerField = " + outerField);
                System.out.println("我自己   name      = " + name);
            }
        }

        Worker w = new Worker();
        w.doWork();
        // localVar = 999;  // 如果在这里修改 localVar，上面 Worker 会编译失败（必须 effectively final）
    }
}
