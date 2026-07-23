package com.itheima;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * 实战案例：用 JUnit4 测试 AccountService
 * <p>
 * 演示单元测试的“正确打开方式”：
 * - 不直接在业务方法上加 @Test（业务方法可能有参/有返回值/是静态的）
 * - 单独写测试类、测试方法（被测方法名 + Test）
 * - @Before 准备数据、@Test 断言、@After 还原/清理，做到「不污染原数据」
 * <p>
 * assertEquals（来自 org.junit.Assert）：断言「期望值」和「实际值」相等。
 * - 相等：静默通过，继续执行下一行
 * - 不相等：抛 AssertionError，当前测试立即失败，报错形如 expected:<700.0> but was:<...>
 * 常用重载：
 * - assertEquals(expected, actual)              比较 int、String、对象（对象用 equals 比较）
 * - assertEquals(expected, actual, delta)       比较 double/float，delta 是允许误差，浮点数必须用这个
 * - assertEquals(message, expected, actual)     失败时显示自定义消息 message
 * 注意：期望值在前、实际值在后，写反了失败信息会误导排查方向。
 * 同家族：assertTrue / assertFalse / assertNull / assertNotNull / assertSame / fail
 */
public class AccountServiceTest {

    private AccountService service;

    // 每个 @Test 运行前都会先执行：初始化两个账户
    @Before
    public void setUp() {
        System.out.println("---- @Before：准备账户数据 ----");
        service = new AccountService();
        service.openAccount("张三", 1000.0);
        service.openAccount("李四", 500.0);
    }

    // 每个 @Test 运行后都会执行：这里没有外部资源要还原，做个占位/日志
    @After
    public void tearDown() {
        System.out.println("---- @After：清理数据 ----");
        service = null;
    }

    // 正常转账：张三转 300 给李四
    @Test
    public void transferTest() {
        service.transfer("张三", "李四", 300.0);
        // 断言：金额应精确变化（第三个参数是 double 比较的允许误差）
        assertEquals("转出方余额错误", 700.0, service.getBalance("张三"), 0.001);
        assertEquals("转入方余额错误", 800.0, service.getBalance("李四"), 0.001);
    }

    // 余额不足：应抛 IllegalStateException，且余额保持不变
    @Test
    public void transferInsufficientTest() {
        try {
            service.transfer("李四", "张三", 9999.0);
            fail("余额不足却没有抛异常");
        } catch (IllegalStateException e) {
            assertEquals("余额不足", e.getMessage());
        }
        // 转账失败后余额不能被改动
        assertEquals(500.0, service.getBalance("李四"), 0.001);
        assertEquals(1000.0, service.getBalance("张三"), 0.001);
    }

    // 非法金额：转 0 或负数应抛 IllegalArgumentException（用 expected 属性写法）
    @Test(expected = IllegalArgumentException.class)
    public void transferInvalidAmountTest() {
        service.transfer("张三", "李四", -1.0);
    }
}
