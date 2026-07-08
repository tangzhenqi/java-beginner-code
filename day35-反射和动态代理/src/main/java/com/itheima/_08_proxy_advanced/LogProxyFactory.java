package com.itheima._08_proxy_advanced;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 拓展用法 1：日志代理。
 * 在不修改业务代码的前提下，给所有方法调用前后自动加上日志。
 * 这正是 Spring AOP 的核心思想。
 */
public class LogProxyFactory {

    @SuppressWarnings("unchecked")
    public static <T> T wrap(T target) {
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("[LOG] -> " + method.getName()
                            + " args=" + Arrays.toString(args));
                    try {
                        Object ret = method.invoke(target, args);
                        System.out.println("[LOG] <- " + method.getName() + " return=" + ret);
                        return ret;
                    } catch (InvocationTargetException e) {
                        // 业务真正抛出的异常被 invoke 包了一层，剥开后再抛
                        System.out.println("[LOG] !! " + method.getName()
                                + " threw " + e.getCause());
                        throw e.getCause();
                    }
                });
    }
}
