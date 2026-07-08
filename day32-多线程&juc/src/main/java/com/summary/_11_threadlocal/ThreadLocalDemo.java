package com.summary._11_threadlocal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ThreadLocal：每个线程一份独立副本。
 * <p>
 * 经典用途：
 * <ul>
 *   <li>把"用户上下文 / traceId"沿调用链共享，避免方法参数到处传</li>
 *   <li>把线程不安全的工具线程私有化（旧的 SimpleDateFormat、解析器等）</li>
 * </ul>
 * <p>
 * ⚠️ 在线程池里使用 ThreadLocal，用完一定要 remove()：
 *    1. 线程会被复用，残留数据会污染下个任务
 *    2. ThreadLocalMap.Entry 的 key 是弱引用而 value 是强引用，
 *       不主动 remove() 容易内存泄漏
 */
public class ThreadLocalDemo {

    private static final ThreadLocal<String> USER_CTX = new ThreadLocal<>();

    public static void main(String[] args) {
        Runnable handle = () -> {
            String name = Thread.currentThread().getName();
            USER_CTX.set(name + "-user-" + (int) (Math.random() * 100));
            try {
                businessLogic();
            } finally {
                USER_CTX.remove(); // 切记
            }
        };
        new Thread(handle, "worker-1").start();
        new Thread(handle, "worker-2").start();
        new Thread(handle, "worker-3").start();
    }

    private static void businessLogic() {
        String now = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);
        // 全程不用层层透传 user 参数
        System.out.println("[" + now + "] " + Thread.currentThread().getName()
                + " 处理用户：" + USER_CTX.get());
    }
}
