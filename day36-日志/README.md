# day36 日志（Logback）

## 知识点
- 日志的作用：替代 `System.out.println`，可分级、可持久化到文件/数据库。
- 日志级别（从低到高）：`TRACE < DEBUG < INFO < WARN < ERROR`；另有 `ALL`/`OFF`。
- 门面 + 实现：代码里用 **SLF4J** 的 `Logger` API，底层由 **Logback** 实现。
- 配置文件 `logback.xml`：控制台 Appender + 文件滚动 Appender + `root` 级别。

## 目录
- [docs/日志.md](docs/日志.md) —— 学习笔记
- [src/main/resources/logback.xml](src/main/resources/logback.xml) —— 日志配置
- [OrderService.java](src/main/java/com/itheima/OrderService.java) —— **实战案例：订单服务日志**

## 运行
```bash
mvn -q compile exec:java -Dexec.mainClass=com.itheima.OrderService
# 或在 IDE 中直接运行 OrderService.main
```
运行后：控制台打印 INFO/WARN/ERROR 日志，同时写入 `logs/order.log`。
（`root level="info"`，所以 DEBUG 日志被过滤，体现级别控制。）
