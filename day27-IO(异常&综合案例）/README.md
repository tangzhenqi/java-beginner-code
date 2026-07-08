# day27 总结与拓展：异常 & 综合案例

本模块对 day27 的异常主题做了系统化归纳：从异常体系、捕获处理，到 throws/throw、自定义异常与异常链，并配套了输入校验综合案例。

> 独立的 Maven 项目，JDK 11+。
>
> 📌 File 类相关内容（构造 / 判断 / 信息 / 增删 / 遍历 / 递归综合案例 / NIO 拓展）已拆分到同级模块 **`day27-IO(File)`**。

## 包结构（按知识点顺序编号）

```
src/main/java/com/itheima/
├── _01_exception_basic/   异常体系 & JVM 默认处理方式
├── _02_exception_handle/  try / catch / 多 catch / Throwable API / finally
├── _03_exception_throw/   throws vs throw、异常作为"信号"
└── _04_exception_custom/  自定义异常 + 输入校验综合案例 + 异常链
```

## 知识点速查表

| 主题 | 关键 API / 概念 | 演示文件 |
|---|---|---|
| 异常体系 | Error / Exception / RuntimeException | `_01_exception_basic/A01_ExceptionHierarchy` |
| JVM 默认处理 | 打印栈轨迹 + 终止当前线程 | `_01_exception_basic/A02_JvmDefaultHandle` |
| try-catch | 灵魂四问 | `_02_exception_handle/B01_TryCatchBasic` |
| 多 catch + `\|` | 子类在父类之前；JDK7 多异常合并 | `_02_exception_handle/B02_MultiCatch` |
| Throwable API | `getMessage / toString / printStackTrace` | `_02_exception_handle/B03_ThrowableApi` |
| finally | 必然执行；切勿在 finally `return` | `_02_exception_handle/B04_Finally` |
| throws vs throw | 声明 vs 抛出；编译时异常强制 throws | `_03_exception_throw/C01_ThrowsVsThrow` |
| 异常即信号 | 拒绝静默处理非法参数 | `_03_exception_throw/C02_ExceptionAsSignal` |
| 自定义异常 | 继承 `RuntimeException`；命名 `XxxException` | `_04_exception_custom/NameFormatException` 等 |
| 输入校验综合 | Scanner + 自定义异常 + 重试循环 | `_04_exception_custom/D01_CustomExceptionDemo` |
| 异常链 | `new XxxException(msg, cause)` | `_04_exception_custom/D02_ExceptionChaining` |

## 常见踩坑提示

1. **finally 中 return 会吞掉 try/catch 的返回值**，反模式。
2. **多 catch 顺序**：子类异常必须写在父类之前，否则编译报错。
3. **编译时异常必须处理**：要么 try-catch，要么方法声明 `throws`，否则编译不过。
4. **自定义异常**：参数错误优先用 `RuntimeException`；强制提醒处理才用受检异常。
5. **重新抛异常时务必带上 cause**：`throw new XxxException(msg, originalEx)`，否则丢失栈轨迹。

## 运行示例

```bash
# 编译
mvn compile

# 运行任意一个 main（示例：输入校验综合案例）
mvn exec:java -Dexec.mainClass=com.itheima._04_exception_custom.D01_CustomExceptionDemo

# 或者用 java 直接运行编译产物
java -cp target/classes com.itheima._03_exception_throw.C01_ThrowsVsThrow
```
