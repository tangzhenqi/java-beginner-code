# day38 单元测试（JUnit4）

## 知识点
- 单元测试：对部分代码（一个方法）单独做测试，绿色通过 / 红色失败。
- `@Test`：标记测试方法（必须无参、无返回值、非静态）。
- 断言 `Assert.assertEquals(...)`：只有断言全部通过，才认为被测方法正确。
- `@Before` / `@After`：每个测试前后执行，用于**准备数据**和**还原数据**，做到不污染原数据。
- 正确用法：单独建测试类 + 测试方法（被测方法名 + `Test`），在其中调用被测方法。

## 目录
- [docs/单元测试.md](docs/单元测试.md) —— 学习笔记
- [AccountService.java](src/main/java/com/itheima/AccountService.java) —— 被测业务类：账户转账
- [AccountServiceTest.java](src/test/java/com/itheima/AccountServiceTest.java) —— **实战案例：JUnit4 测试转账逻辑**

## 运行
```bash
mvn -q test
```
覆盖三种场景：正常转账、余额不足抛异常、非法金额抛异常。
