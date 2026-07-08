package com.itheima._06_waitnotify;

/**
 * 共享桌子：控制生产者和消费者的协作状态。
 */
public class Desk {

    /** 桌上是否有面条：0 没有，1 有 */
    public static int foodFlag = 0;

    /** 还需要做 / 吃的总碗数 */
    public static int count = 10;

    /** 锁对象（必须保证生产者和消费者使用同一把锁） */
    public static final Object lock = new Object();
}
