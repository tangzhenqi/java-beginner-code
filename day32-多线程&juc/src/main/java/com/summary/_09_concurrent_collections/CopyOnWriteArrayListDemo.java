package com.summary._09_concurrent_collections;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList：写时复制。
 * <ul>
 *   <li>读操作：完全无锁，性能极高（直接读底层 final 数组）</li>
 *   <li>写操作：内部加锁，先复制一份数组，修改完再 set 回去</li>
 * </ul>
 * 适合 "读多写少 + 集合不大" 的场景，例如订阅者列表、监听器列表。
 */
public class CopyOnWriteArrayListDemo {

    public static void main(String[] args) throws InterruptedException {
        List<String> listeners = new CopyOnWriteArrayList<>();
        listeners.add("listener-A");
        listeners.add("listener-B");

        Thread reader = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                // 即使遍历过程中其它线程在修改，也不会抛 ConcurrentModificationException
                listeners.forEach(System.out::println);
                System.out.println("------");
                sleep(200);
            }
        });

        Thread writer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                listeners.add("listener-NEW-" + i);
                sleep(150);
            }
        });

        reader.start();
        writer.start();
        reader.join();
        writer.join();

        System.out.println("最终：" + listeners);
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
