package com.itheima._08_proxy_advanced;

/**
 * 拓展演示：把多个代理"叠加"在同一个目标上，体会切面组合的能力。
 *
 *   real           原始实现
 *     ↓
 *   validating     先做参数校验
 *     ↓
 *   logging        再打日志
 *     ↓
 *   timing         最外层统计耗时
 *
 * 调用 timing.add(...) 时会从外向内依次穿过每一层，再从内向外返回。
 */
public class ProxyAdvancedDemo {

    public static void main(String[] args) {
        Calculator real = new CalculatorImpl();

        Calculator validating = ValidatingProxyFactory.wrap(real);
        Calculator logging = LogProxyFactory.wrap(validating);
        Calculator timing = TimingProxyFactory.wrap(logging);

        System.out.println("==== 正常调用 add ====");
        timing.add(3, 5);

        System.out.println();
        System.out.println("==== 触发参数校验 div(10, 0) ====");
        try {
            timing.div(10, 0);
        } catch (Exception e) {
            // 注意：经过反射 invoke 的异常会被包成 InvocationTargetException，
            // 但 JDK Proxy 又会把 Handler 抛出的"原始"异常直接抛出来。
            System.out.println("捕获到: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
}
