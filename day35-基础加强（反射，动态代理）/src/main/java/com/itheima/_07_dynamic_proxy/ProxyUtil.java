package com.itheima._07_dynamic_proxy;

import java.lang.reflect.Proxy;

/**
 * 知识点：用 Proxy.newProxyInstance 给"大明星"生成一个经纪人代理。
 *
 * Proxy.newProxyInstance(ClassLoader, Class[], InvocationHandler)
 *   1) ClassLoader      —— 用哪个类加载器加载生成的代理类
 *   2) Class[]          —— 这个代理实现哪些接口（决定了它"长什么样"）
 *   3) InvocationHandler—— 调用任意方法时，统一进入 invoke(...) 回调
 *
 * 代理对象在 IDE 看是个 Star，但底层是 JDK 在运行期动态生成的 $Proxy0 类，
 * 它把所有方法调用都转发到我们写的 InvocationHandler 里。
 */
public class ProxyUtil {

    public static Star createProxy(BigStar bigStar) {
        return (Star) Proxy.newProxyInstance(
                ProxyUtil.class.getClassLoader(),
                new Class[]{Star.class},
                (proxy, method, args) -> {
                    // 方法调用前：经纪人干活
                    if ("sing".equals(method.getName())) {
                        System.out.println("[经纪人] 准备话筒，先收钱");
                    } else if ("dance".equals(method.getName())) {
                        System.out.println("[经纪人] 准备场地，先收钱");
                    }
                    // 真正的事还是大明星本人做
                    return method.invoke(bigStar, args);
                }
        );
    }
}
