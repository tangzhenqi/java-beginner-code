package com.itheima._07_dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 与 ProxyUtil 功能完全相同，只是把 InvocationHandler 用【匿名内部类】方式书写，
 * 用来和 ProxyUtil 里的【Lambda 表达式】写法做对照。
 *
 * 匿名内部类相比 Lambda 多写的部分：
 *   1) new InvocationHandler() { ... }  —— 显式 new 出接口实现
 *   2) @Override public Object invoke(...)  —— 完整方法签名、返回类型、注解
 *   3) 参数类型 Object/Method/Object[]      —— Lambda 里可省略靠推断
 *   4) throws Throwable
 * 方法体逻辑两者完全一致。
 *
 * 之所以两种都能写，是因为 InvocationHandler 是函数式接口（只有 invoke 一个抽象方法）。
 */
public class AnonymousProxyUtil {

    public static Star createProxy(BigStar bigStar) {
        return (Star) Proxy.newProxyInstance(
                AnonymousProxyUtil.class.getClassLoader(),
                new Class[]{Star.class},
                new InvocationHandler() {          // 显式 new 接口（匿名内部类）
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 方法调用前：经纪人干活
                        if ("sing".equals(method.getName())) {
                            System.out.println("[经纪人] 准备话筒，先收钱");
                        } else if ("dance".equals(method.getName())) {
                            System.out.println("[经纪人] 准备场地，先收钱");
                        }
                        // 真正的事还是大明星本人做
                        return method.invoke(bigStar, args);
                    }
                }
        );
    }
}
