package com.itheima._06_waitnotify;

/**
 * 生产者—消费者（等待唤醒机制）
 *
 * 三个关键方法（必须在 synchronized 同步代码块内调用，且锁对象要一致）：
 *   wait()       释放锁并进入 WAITING
 *   notify()     随机唤醒一个等待线程
 *   notifyAll()  唤醒所有等待线程（推荐）
 *
 * 实现思路：
 *   1. 循环 + 同步代码块
 *   2. 判断共享数据是否已结束（结束就 break）
 *   3. 未结束：判断当前是否轮到自己，不该自己干就 wait()，该干就执行并 notifyAll()
 */
public class WaitNotifyDemo {
    public static void main(String[] args) {
        Cook c = new Cook();
        Foodie f = new Foodie();
        c.setName("厨师");
        f.setName("吃货");
        c.start();
        f.start();
    }
}
