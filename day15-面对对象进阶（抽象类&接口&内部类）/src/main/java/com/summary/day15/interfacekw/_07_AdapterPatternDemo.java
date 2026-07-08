package com.summary.day15.interfacekw;

/**
 * 适配器模式：
 *  痛点：接口里有很多抽象方法，但实现类常常只关心其中一两个，
 *       直接 implements 会被迫写一堆空方法体，很啰嗦。
 *  解决：再写一个"抽象适配器类" XxxAdapter，对接口的所有方法提供空实现；
 *       业务类去 extends 这个适配器，只重写自己关心的方法即可。
 *
 *  JDK 中的例子：WindowAdapter / MouseAdapter / KeyAdapter 等。
 */
public class _07_AdapterPatternDemo {

    public static void main(String[] args) {
        WindowListener listener = new MyAppListener();

        // 模拟事件触发
        listener.onOpen();
        listener.onClose();
        listener.onResize();
        listener.onMove();
        listener.onMinimize();
    }
}

/** 一个有多个事件的监听接口。 */
interface WindowListener {
    void onOpen();
    void onClose();
    void onResize();
    void onMove();
    void onMinimize();
}

/** 抽象适配器：对每个方法都提供空实现。一定要写成 abstract，避免被直接 new。 */
abstract class WindowAdapter implements WindowListener {
    @Override public void onOpen()     { }
    @Override public void onClose()    { }
    @Override public void onResize()   { }
    @Override public void onMove()     { }
    @Override public void onMinimize() { }
}

/** 业务类只重写自己关心的方法，其他保持默认空实现。 */
class MyAppListener extends WindowAdapter {
    @Override
    public void onClose() {
        System.out.println("MyApp：窗口关闭，做一些清理工作");
    }
    @Override
    public void onResize() {
        System.out.println("MyApp：窗口大小改变，重新布局");
    }
}
