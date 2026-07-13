# day40 注解

## 知识点
- 注释给程序员看（编译后擦除）；注解给虚拟机/框架看。
- 内置注解：`@Override`、`@Deprecated`、`@SuppressWarnings`。
- 自定义注解 `@interface`；特殊属性 `value`（唯一属性时可省略属性名）。
- 元注解：`@Target`（用在哪）、`@Retention`（保留到何时；反射解析必须用 `RUNTIME`）。
- **注解解析**：注解本身没功能，靠反射读取后决定做什么——这是所有框架的底层套路。

## 目录
- [docs/注解.md](docs/注解.md) —— 学习笔记
- 案例一 · 模拟 JUnit 测试框架（[mytest/](src/main/java/com/itheima/mytest/)）
  - [MyTest.java](src/main/java/com/itheima/mytest/MyTest.java) 自定义注解
  - [MyTestRunner.java](src/main/java/com/itheima/mytest/MyTestRunner.java) 反射扫描并执行被 `@MyTest` 标注的方法
- 案例二 · 注解 + 反射生成建表 SQL（[orm/](src/main/java/com/itheima/orm/)）
  - [@DBTable](src/main/java/com/itheima/orm/DBTable.java) / [@DBColumn](src/main/java/com/itheima/orm/DBColumn.java)
  - [User.java](src/main/java/com/itheima/orm/User.java) 用注解描述表/列映射
  - [CreateTableSqlGenerator.java](src/main/java/com/itheima/orm/CreateTableSqlGenerator.java) 反射生成 `CREATE TABLE`

## 运行
```bash
mvn -q compile exec:java -Dexec.mainClass=com.itheima.mytest.MyTestRunner
mvn -q compile exec:java -Dexec.mainClass=com.itheima.orm.CreateTableSqlGenerator
```
