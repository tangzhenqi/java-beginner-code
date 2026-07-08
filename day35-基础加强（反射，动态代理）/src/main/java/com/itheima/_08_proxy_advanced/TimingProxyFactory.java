package com.itheima._08_proxy_advanced;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * 拓展用法 2：性能监控代理。
 * 在方法前后记录纳秒级耗时，统一打印。
 */
public class TimingProxyFactory {

    @SuppressWarnings("unchecked")
    public static <T> T wrap(T target) {
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    long start = System.nanoTime();
                    try {
                        return method.invoke(target, args);
                    } catch (InvocationTargetException e) {
                        // 把真正抛的异常原样向上抛出，避免被包成 UndeclaredThrowableException
                        throw e.getCause();
                    } finally {
                        long cost = System.nanoTime() - start;
                        System.out.println("[TIMING] " + method.getName()
                                + " 耗时 " + cost + " ns");
                    }
                });
    }
}
