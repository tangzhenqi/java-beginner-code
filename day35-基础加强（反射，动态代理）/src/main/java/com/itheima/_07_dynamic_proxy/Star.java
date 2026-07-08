package com.itheima._07_dynamic_proxy;

/**
 * 被代理对象必须实现的接口。
 * JDK 动态代理只能基于"接口"生成代理，这是它的硬性约束。
 */
public interface Star {

    String sing(String songName);

    void dance();
}
