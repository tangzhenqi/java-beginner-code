# 从 classpath 加载 properties 配置文件

## 一、示例代码

```java
// 1. 从 classpath 加载配置
Properties prop = new Properties();
try (InputStream is = ReflectConfigDemo.class
        .getClassLoader()
        .getResourceAsStream("prop.properties")) {
    prop.load(is);
}
```

## 二、逐行解释

| 代码 | 作用 |
|---|---|
| `new Properties()` | 创建配置对象，本质是 `Hashtable<Object,Object>`，专门存储 `key=value` 并提供读写 `.properties` 的方法 |
| `ReflectConfigDemo.class` | 获取当前类的 `Class` 对象 |
| `.getClassLoader()` | 拿到加载该类的**类加载器** |
| `.getResourceAsStream("prop.properties")` | 让类加载器从 **classpath 根**查找该文件，返回字节流 `InputStream` |
| `try (...)` | **try-with-resources** 语法，`InputStream` 实现了 `AutoCloseable`，无论正常或异常都会**自动关闭**流 |
| `prop.load(is)` | 从流中读取并解析内容，把每行 `key=value` 存入 `prop`，之后用 `prop.getProperty("key")` 取值 |

## 三、什么是 classpath 根

classpath 根 = Java 运行时查找 `.class` 和资源文件（`.properties`/`.xml`/图片等）的**起始目录**，类似文件系统的 `/`。

### Maven 项目目录结构

```
day35-反射和动态代理/
├── src/main/java/          ← Java 源代码
│   └── com/itheima/_06_reflect_config/ReflectConfigDemo.java
└── src/main/resources/     ← 资源文件放这里
    └── prop.properties
```

### 编译后（真正的 classpath 根）

Maven `compile` 后，两个目录内容**合并**输出到 `target/classes/`：

```
target/classes/          ← 这个目录就是 classpath 根
├── com/itheima/_06_reflect_config/ReflectConfigDemo.class
└── prop.properties       ← 直接躺在根下
```

### 路径都相对于根计算

| 代码写的路径 | 实际找的文件 |
|---|---|
| `getResourceAsStream("prop.properties")` | `target/classes/prop.properties` |
| `getResourceAsStream("config/db.properties")` | `target/classes/config/db.properties` |
| `getResourceAsStream("com/itheima/a.txt")` | `target/classes/com/itheima/a.txt` |

## 四、两种 getResourceAsStream 的区别

```java
// 方式1: 类加载器的方法 —— 路径总是从 classpath 根开始，不能带前导 /
ReflectConfigDemo.class.getClassLoader().getResourceAsStream("prop.properties")
// → 找 target/classes/prop.properties

// 方式2: Class 的方法 —— 默认从"当前类所在包"开始找
ReflectConfigDemo.class.getResourceAsStream("prop.properties")
// → 找 com/itheima/_06_reflect_config/prop.properties

// 方式2 加前导 / 才是从根开始，等价于方式1
ReflectConfigDemo.class.getResourceAsStream("/prop.properties")
```

## 五、打成 jar 包之后

classpath 根的概念依然成立，"目录"变成 **jar 包内部的根路径**。资源被打进 jar，`getResourceAsStream` 从 jar 根读取——因此比 `new File(...)` 更可靠：**打包后仍能正常工作**，而依赖文件系统路径的 `File` 往往会失效。

## 六、注意事项

- 若文件在 classpath 中找不到，`getResourceAsStream` 返回 `null`，此时 `prop.load(null)` 会抛 **NullPointerException**。健壮写法应在 `load` 前判断 `is != null`。
- 不带前导 `/` 的 `getClassLoader().getResourceAsStream("x")` 与带前导 `/` 的 `class.getResourceAsStream("/x")` 效果等价，都是从根开始查找。

## 七、一句话总结

**classpath 根 = 编译输出目录 `target/classes/`（或 jar 包内部的根），所有资源路径都相对它计算。**
