package com.summary._10_completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFuture：JDK 8 引入的"现代异步"。
 * <p>
 * 相比 FutureTask 必须 get() 阻塞拿结果，CompletableFuture 支持：
 * <ul>
 *   <li>链式回调：thenApply / thenAccept / thenRun</li>
 *   <li>组合：thenCombine（两个 Future 都完成后合并）</li>
 *   <li>等多个：allOf / anyOf</li>
 *   <li>异常兜底：exceptionally / handle</li>
 * </ul>
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1. 链式调用
        CompletableFuture<String> f1 = CompletableFuture
                .supplyAsync(() -> {
                    sleep(300);
                    return "Tom";
                })
                .thenApply(name -> "Hello, " + name)
                .thenApply(String::toUpperCase);
        System.out.println(f1.get());

        // 2. 异常兜底
        CompletableFuture<Integer> f2 = CompletableFuture
                .supplyAsync(() -> {
                    if (true) throw new RuntimeException("boom");
                    return 1;
                })
                .exceptionally(ex -> {
                    System.out.println("捕获异常：" + ex.getMessage());
                    return -1;
                });
        System.out.println("兜底结果：" + f2.get());

        // 3. 并行调用 + 合并
        CompletableFuture<Integer> price = CompletableFuture.supplyAsync(() -> { sleep(200); return 100; });
        CompletableFuture<Integer> tax   = CompletableFuture.supplyAsync(() -> { sleep(300); return 13; });
        CompletableFuture<Integer> total = price.thenCombine(tax, Integer::sum);
        System.out.println("总价（含税）：" + total.get());
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
