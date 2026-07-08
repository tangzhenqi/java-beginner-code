package com.itheima._08_proxy_advanced;

import java.lang.reflect.Proxy;

/**
 * 拓展用法 3：参数校验代理。
 * 这里演示"除法的除数不能是 0"：把这种横切关心的校验逻辑放在代理里，
 * 业务实现 CalculatorImpl 就可以保持干净。
 */
public class ValidatingProxyFactory {

    public static Calculator wrap(Calculator target) {
        return (Calculator) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                new Class[]{Calculator.class},
                (proxy, method, args) -> {
                    if ("div".equals(method.getName())
                            && args != null
                            && args.length == 2
                            && Integer.valueOf(0).equals(args[1])) {
                        throw new IllegalArgumentException("除数不能为 0");
                    }
                    return method.invoke(target, args);
                });
    }
}
