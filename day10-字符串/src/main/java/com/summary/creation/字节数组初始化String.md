# 为什么可以用字节数组初始化 String

> 对应代码：[StringCreationDemo.java](StringCreationDemo.java) 第 28–30 行
>
> ```java
> // 5) 字节数组（97 -> 'a'）。网络传输的数据通常是字节，要转成字符串就用它。
> byte[] bytes = {97, 98, 99, 100};
> String s5 = new String(bytes);   // "abcd"
> ```

字符串本质上就是**一串字节按某种字符编码解析出来的字符序列**，所以只要给出 `字节 + 编码规则`，就能还原成 String。

---

## 1. String 内部就是字节

从 JDK 9 开始，`String` 内部存储就是：

```java
private final byte[] value;
```

（JDK 9 之前是 `char[]`）。

所以"用 `byte[]` 构造 String"其实是最贴近底层的方式——只是把一段字节交给它，并告诉它怎么解读。

---

## 2. 字节 → 字符 需要"编码表"

计算机里只有 0/1，字符 `'a'` 之所以是 `'a'`，是因为有一张约定的表（编码）说："数字 97 代表 'a'"。

常见的对应关系（ASCII / UTF-8 单字节段）：

| 字节值 | 字符 | 说明 |
|--------|------|------|
| 97     | a    | 小写字母 a |
| 98     | b    | 小写字母 b |
| 99     | c    | 小写字母 c |
| 100    | d    | 小写字母 d |

所以代码中的：

```java
byte[] bytes = {97, 98, 99, 100};
String s5 = new String(bytes);   // "abcd"
```

JVM 按当前平台默认字符集（通常是 UTF-8）把每个字节翻译成字符，得到 `"abcd"`。

---

## 3. 为什么需要这个构造方法（应用场景）

注释里那句"**网络传输的数据通常是字节**"就是核心动机：

- **网络 / 文件 IO**：`InputStream.read()` 读出来的是 `byte[]`，不是 `String`
- **Socket、HTTP body、文件内容**：底层全是字节流
- 拿到字节后，要变成人类可读的文本，就靠 `new String(bytes, charset)`

典型流程：

```
网卡 / 磁盘 ──字节流──> byte[] ──new String(bytes, UTF_8)──> "可读文本"
```

---

## 4. 重要提醒：最好显式指定编码

`new String(bytes)` 用的是**平台默认编码**，不同操作系统可能不一样（Windows 中文版常是 GBK，Linux / Mac 多是 UTF-8），跨平台容易乱码。

生产代码建议显式指定字符集：

```java
import java.nio.charset.StandardCharsets;

String s5 = new String(bytes, StandardCharsets.UTF_8);
```

---

## 5. 反向操作：String → byte[]

既然能从字节构造，也能反过来拿到字节，常用于网络发送、写文件：

```java
String s = "abcd";
byte[] data = s.getBytes(StandardCharsets.UTF_8);  // {97, 98, 99, 100}
```

---

## 一句话总结

> **字符串 = 字节 + 编码规则**
>
> `new String(byte[])` 就是把"字节"和"规则"组装回"字符"的过程，
> 而 `getBytes()` 是反过来把字符按规则拆回字节。
