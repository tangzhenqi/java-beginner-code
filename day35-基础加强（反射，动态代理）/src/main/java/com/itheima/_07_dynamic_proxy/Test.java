package com.itheima._07_dynamic_proxy;

public class Test {
    public static void main(String[] args) {
        BigStar bigStar = new BigStar("鸡哥");
        Star proxy = ProxyUtil.createProxy(bigStar);

        String reply = proxy.sing("只因你太美");
        System.out.println("大明星回复: " + reply);

        proxy.dance();
    }
}
