package com.itheima;

import java.util.HashMap;
import java.util.Map;

/**
 * 被测业务类：账户服务（模拟银行转账）
 * <p>
 * 这类涉及金额、余额校验的代码，最适合用单元测试来保证正确性——
 * 手工点着测太容易漏掉边界情况（余额不足、金额为负、账户不存在）。
 */
public class AccountService {

    // 账户 -> 余额
    private final Map<String, Double> accounts = new HashMap<>();

    public void openAccount(String name, double balance) {
        accounts.put(name, balance);
    }

    public double getBalance(String name) {
        Double balance = accounts.get(name);
        if (balance == null) {
            throw new IllegalArgumentException("账户不存在：" + name);
        }
        return balance;
    }

    /**
     * 从 from 向 to 转账 amount 元。
     *
     * @throws IllegalArgumentException 账户不存在或金额非法
     * @throws IllegalStateException    余额不足
     */
    public void transfer(String from, String to, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("转账金额必须大于 0");
        }
        double fromBalance = getBalance(from);
        double toBalance = getBalance(to);
        if (fromBalance < amount) {
            throw new IllegalStateException("余额不足");
        }
        accounts.put(from, fromBalance - amount);
        accounts.put(to, toBalance + amount);
    }
}
