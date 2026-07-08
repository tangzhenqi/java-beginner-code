package com.summary._07_atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicReference：原子地更新一个引用。
 * <p>
 * 适合"读 → 计算 → CAS 写回"的乐观并发场景，例如更新一个不可变对象的引用。
 */
public class AtomicReferenceDemo {

    static final class Account {
        final String name;
        final int balance;

        Account(String name, int balance) {
            this.name = name;
            this.balance = balance;
        }

        @Override
        public String toString() {
            return "Account{name=" + name + ", balance=" + balance + "}";
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicReference<Account> ref = new AtomicReference<>(new Account("Tom", 100));

        Runnable deposit = () -> {
            for (int i = 0; i < 1000; i++) {
                Account cur;
                Account next;
                do {
                    cur = ref.get();
                    next = new Account(cur.name, cur.balance + 1);
                } while (!ref.compareAndSet(cur, next));
            }
        };

        Thread t1 = new Thread(deposit);
        Thread t2 = new Thread(deposit);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("最终账户：" + ref.get() + "（期望 balance = 2100）");
    }
}
