package com.itheima._01_create;

import java.util.concurrent.Callable;

/**
 * 方式三：实现 Callable 接口（JDK 5+）
 * 优点：可以获取多线程执行的返回结果，可以抛出异常
 * 缺点：编码相对复杂
 */
public class MyCallable implements Callable<Integer> {

    private final int n;

    public MyCallable(int n) {
        this.n = n;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }
}
