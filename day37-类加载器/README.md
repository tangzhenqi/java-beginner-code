# day37 类加载器

## 知识点
- 类加载时机：用到才加载（创建实例、调用类方法、反射、初始化子类、`java` 运行主类…）。
- 类加载过程：**加载 → 验证 → 准备 → 解析 → 初始化**。
- 三级类加载器：`Bootstrap`（启动，`null`）→ `Platform`（平台）→ `System`（应用/系统）。
- 双亲委派：先向上委托父加载器，父加载不了子加载器再自己加载。
- `ClassLoader.getResourceAsStream(name)`：加载 classpath 上的资源文件。

## 目录
- [docs/类加载器.md](docs/类加载器.md) —— 学习笔记（含图）
- [ClassLoaderDemo.java](src/main/java/com/itheima/ClassLoaderDemo.java) —— 三级加载器演示
- [DbConfigLoader.java](src/main/java/com/itheima/DbConfigLoader.java) —— **实战案例：类加载器读取 db.properties**
- [src/main/resources/db.properties](src/main/resources/db.properties) —— classpath 配置文件

## 运行
```bash
mvn -q compile exec:java -Dexec.mainClass=com.itheima.ClassLoaderDemo
mvn -q compile exec:java -Dexec.mainClass=com.itheima.DbConfigLoader
```
