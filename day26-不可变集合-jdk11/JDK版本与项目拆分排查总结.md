# day26 build 失败排查 & JDK 知识总结

> 起因：`day26-集合（Stream流&方法引用）` 项目在 VSCode 里 build failed。
> 经过一整轮排查后，把项目按 JDK 版本拆分，并梳理了相关知识点。

---

## 一、问题排查链（按时间顺序）

### 1. 第一层：pom 要 JDK 11，但系统只有 JDK 8

- day26 的 `pom.xml` 配置 `<maven.compiler.source>11</maven.compiler.source>`
- 因为 `_01_immutable/` 目录下 4 个类用了 `List.of` / `Set.of` / `Map.of` / `Map.ofEntries`（JDK 9+ API）
- 但系统 `java -version` 显示 `1.8.0_451`，根本没装 JDK 11

**误操作**：把 pom 从 11 改成 1.8。结果 `List.of` 等调用全部"找不到符号"。

### 2. 第二层：装了 JDK 11，但 `JAVA_HOME` 没切

```
java -version        →  11.0.31      ✅ PATH 里 java 切了
JAVA_HOME            →  jdk-1.8.jdk  ❌ 仍指向 JDK 8
mvn -version         →  Java 1.8.0_451  ❌ Maven 跟随 JAVA_HOME
```

**关键点**：**Maven 用的是 `JAVA_HOME`，不是 PATH 里的 java**。

修复：

```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 11)
export PATH="$JAVA_HOME/bin:$PATH"
```

### 3. 第三层：VSCode 不读 shell 的 `JAVA_HOME`

VSCode 跟 IDEA 一样有自己的 Java 配置。截图发现它在用：

```
/Library/Internet Plug-Ins/JavaAppletPlugin.plugin/Contents/Home/bin/java   ← JDK 8 Applet Plugin
```

修复方式（user `settings.json`）：

```json
{
  "java.jdt.ls.java.home": "/Library/Java/JavaVirtualMachines/jdk-11.jdk/Contents/Home",
  "java.configuration.runtimes": [
    {
      "name": "JavaSE-1.8",
      "path": "/Library/Java/JavaVirtualMachines/jdk-1.8.jdk/Contents/Home"
    },
    {
      "name": "JavaSE-11",
      "path": "/Library/Java/JavaVirtualMachines/jdk-11.jdk/Contents/Home",
      "default": true
    }
  ]
}
```

**关键点**：
- `name` 必须写 `JavaSE-X` 这种格式
- 至少一个要带 `"default": true`
- 保存后必须 `Cmd+Shift+P → Java: Clean Java Language Server Workspace → Reload and delete`

---

## 二、最终解决方案：项目拆分

不再为同一个 day26 折腾混合 JDK，把 JDK 11 代码独立成新项目。

### 拆分前

```
day26-集合（Stream流&方法引用）/          ← 整个项目卡在 JDK 11
└── src/main/java/com/itheima/
    ├── _01_immutable/             ← 用 List.of 等，需要 JDK 11
    ├── _02_stream_create/         ← 只用 JDK 8 API
    ├── _03_stream_intermediate/   ← 只用 JDK 8 API
    ├── _04_stream_terminal/
    ├── _05_method_reference/
    └── _06_practice/
```

### 拆分后

```
Java Beginner code/
├── day26-集合（Stream流&方法引用）/      ← JDK 1.8
│   ├── pom.xml                         （source/target = 1.8）
│   └── src/main/java/com/itheima/
│       ├── _02_stream_create/
│       ├── _03_stream_intermediate/
│       ├── _04_stream_terminal/
│       ├── _05_method_reference/
│       └── _06_practice/
│
└── day26-immutable/                    ← JDK 11，独立 Maven 项目
    ├── pom.xml                         （source/target = 11）
    └── src/main/java/com/itheima/_01_immutable/
        ├── ImmutableListDemo.java
        ├── ImmutableSetDemo.java
        ├── ImmutableMapDemo.java
        └── ImmutableMapEntriesDemo.java
```

两个完全独立的 Maven 项目，互不影响。VSCode 的 Java 扩展会按各自 `pom` 里的 `maven.compiler.target` 自动匹配对应 JDK runtime。

---

## 三、知识点总结

### 1. `List.of(...)` vs `Arrays.asList(...)`

| 维度 | `List.of(...)` | `Arrays.asList(...)` |
|---|---|---|
| 引入版本 | **JDK 9+** | JDK 1.2 |
| 返回类型 | `ImmutableCollections.ListN` | `Arrays$ArrayList`（**不是** `java.util.ArrayList`） |
| `add` / `remove` | ❌ 抛 `UnsupportedOperationException` | ❌ 同样抛 |
| `set(i, x)` 改元素 | ❌ 抛异常 | ✅ **可以** |
| 允许 `null` 元素 | ❌ `NullPointerException` | ✅ 允许 |
| 与原数组关系 | 无 | **视图**：改数组↔改 List |

**选择**：
- 固定数据/只读 → `List.of(...)`
- 把数组当 List 用（要 `set`） → `Arrays.asList(arr)`
- 要能 `add` / `remove` → `new ArrayList<>(List.of(...))`

**常见坑**：

```java
int[] nums = {1, 2, 3};
List<int[]> wrong = Arrays.asList(nums);   // 长度 1！基本类型数组被当成一个元素
```

### 2. JDK 11 能完全兼容 JDK 8 吗？

**大多数情况兼容，但不是 100%**。

✅ **兼容**：语法（lambda/Stream/方法引用）、核心 API（`java.util.*`、`java.io.*`、`java.time.*`）、字节码（JDK 8 编译的 .class 在 JDK 11 上直接能跑）。

❌ **不兼容 / 需要注意**：

| 移除/变更 | 影响 |
|---|---|
| Java EE 模块全部移除：JAXB / JAX-WS / `javax.activation` / `javax.annotation` / CORBA | JDK 11 必须单独引依赖 |
| JavaFX 从 JDK 剥离 | 要引 OpenJFX |
| `sun.*` / `com.sun.*` 内部 API 封闭 | 老的字节码增强类库（早期 Lombok、CGLib、ASM）反射访问会报 `IllegalAccessException` |
| 默认 GC 从 Parallel 变 G1 | 延迟敏感应用要重新调优 |
| `-source 1.5` 及以下不支持 | 上古项目要先升 1.6+ |
| 工具：`jhat`、`javah`、`policytool` 移除 | 影响构建脚本 |

### 3. `source` / `target` / `release` 区别

| 参数 | 控制什么 |
|---|---|
| `source` | 能用什么**语法**（写代码时） |
| `target` | 生成什么**字节码版本**（产物 .class） |
| `release` | 上面两个 + 能调用什么 **API**（一锅端，最严格） |

**核心问题**：`source/target=1.8` 不够安全。例：

```java
// pom 里 source=1.8, target=1.8，但实际用 JDK 11 编译
List<Integer> list = List.of(1, 2, 3);
```

- ✅ **编译通过**（source/target 不检查 API）
- ❌ **JDK 8 运行时炸**：`NoSuchMethodError: List.of`

**`--release` 解决方案**（JDK 9 才有的参数）：

```xml
<properties>
    <maven.compiler.release>8</maven.compiler.release>
</properties>
```

`release=8` 会同时锁住：① 语法 ② 字节码版本 ③ **API 范围**（编译器自带 JDK 8 API 签名表，调用 JDK 9+ API 直接编译报错）。

**组合对照表**（机器装 JDK 11，编译 `List.of(...)`，部署到 JDK 8）：

| 配置 | 编译时 | JDK 8 运行 |
|---|---|---|
| `source=8, target=8` | ✅ | ❌ `NoSuchMethodError` |
| `source=11, target=8` | ✅ | ❌ 同上 |
| `source=8, target=11` | ✅ | ❌ `UnsupportedClassVersionError` |
| `release=8` | ❌ **编译就拦下** | —— |
| `release=11` | ✅ | ❌ class major=55 拒载 |

**建议**：
- 新项目 → 用 `release`，避免误用新 API
- 兼容老 JDK 8 部署 → 必须用 `release=8`，不能用 `source/target=1.8`
- 用 `source/target` 的唯一理由：JDK 8 javac 不认识 `release` 参数；或 source ≠ target 的特殊场景

---

## 四、教训 / 排查思路沉淀

1. **build 失败的报错弹窗通常没用**，要去 `Problems` 面板 / `Output` 面板的 `Language Support for Java` 通道看真实错误。

2. **Java 工具链有三层 JDK 配置，互不相通**：
   - shell PATH 的 `java` —— 命令行直接跑代码用
   - shell `JAVA_HOME` —— Maven、Gradle 用
   - IDE 自己的 runtime 设置（VSCode `java.configuration.runtimes` / IDEA Project SDK）—— IDE 内 build/run 用

   三处必须同步切，否则会出现"命令行能跑、IDE 不行"或反之的怪现象。

3. **同一个 Maven 项目混用不同 JDK 版本不现实**。最干净的做法就是按 JDK 版本拆成独立项目。

4. **Maven 报 "Aggregator projects require 'pom' as packaging"** —— 一个 pom 不能同时 `packaging=jar` + 含有 `<modules>`，要么纯父 pom（packaging=pom）、要么纯子模块。

5. **看 `java -version` 不够，要同时看 `echo $JAVA_HOME` 和 `mvn -version`**，三者一致才算真正切干净。
