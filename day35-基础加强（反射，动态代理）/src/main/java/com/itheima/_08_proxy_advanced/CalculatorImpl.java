package com.itheima._08_proxy_advanced;

public class CalculatorImpl implements Calculator {

    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int div(int a, int b) {
        return a / b;
    }
}
