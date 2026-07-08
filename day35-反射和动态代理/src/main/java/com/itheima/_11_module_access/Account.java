package com.itheima._11_module_access;

/**
 * 一个普通的自定义类，带 private 字段，用来对比"翻自己的类"能被反射突破。
 */
public class Account {
    private int balance;

    public Account(int balance) {
        this.balance = balance;
    }
}
