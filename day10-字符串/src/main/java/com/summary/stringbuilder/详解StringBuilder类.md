# StringBuilder 类详解

## 一、它是什么？

`StringBuilder` 是 Java 提供的一个**可变的字符串容器**，位于 `java.lang` 包下（所以不用 import 就能直接用）。

它的核心定位就一句话：

> **String 是不可变的，每次拼接都会生成新对象；StringBuilder 是可变的，所有修改都在同一个对象上进行，效率高。**

---

## 二、为什么需要它？（和 String 对比）

看一段代码：

```java
String s = "";
for (int i = 0; i < 10000; i++) {
    s += i;   // 每次都会创建一个新的 String 对象！
}
```

上面这段代码会创建大约 **1 万个临时 String 对象**，性能极差。

用 StringBuilder 重写：

```java
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 10000; i++) {
    sb.append(i);   // 始终在同一个对象上追加
}
String s = sb.toString();
```

**始终只有一个对象**，效率高出几个数量级。

---

## 三、内部原理（简单理解）

StringBuilder 内部维护着两样东西：

| 字段 | 含义 |
|---|---|
| `char[] value` | 真正存放字符的数组（**默认容量 16**） |
| `int count`    | 当前实际存放了多少个字符（即 `length()`） |

当你不断 `append`，数组装不下时，它会**自动扩容**（一般是「旧容量 × 2 + 2」），把原数据复制到新数组里。

---

## 四、常用构造方法

| 构造方法 | 说明 |
|---|---|
| `new StringBuilder()` | 创建空的，初始容量 16 |
| `new StringBuilder(int capacity)` | 指定初始容量（已知大小时推荐，避免扩容） |
| `new StringBuilder(String str)` | 用一个已有字符串初始化，容量 = `str.length() + 16` |

---

## 五、常用方法（重点）

下面这些**几乎都返回 `this`**，所以可以**链式调用**。

| 方法 | 作用 | 示例 |
|---|---|---|
| `append(任意类型)` | 在末尾追加内容 | `sb.append("abc").append(123)` |
| `insert(int offset, 内容)` | 在指定位置插入 | `sb.insert(0, "前缀")` |
| `delete(int start, int end)` | 删除 [start, end) 范围的字符 | `sb.delete(2, 5)` |
| `deleteCharAt(int index)` | 删除指定位置的一个字符 | `sb.deleteCharAt(0)` |
| `replace(int start, int end, String str)` | 替换一段内容 | `sb.replace(0, 3, "XX")` |
| `reverse()` | 反转字符串 | `sb.reverse()` |
| `setCharAt(int index, char ch)` | 修改某个位置的字符 | `sb.setCharAt(0, 'A')` |

**这些方法不返回 `this`：**

| 方法 | 作用 |
|---|---|
| `length()` | 返回当前字符个数 |
| `capacity()` | 返回当前数组容量 |
| `charAt(int index)` | 取某个位置的字符 |
| `indexOf(String str)` | 查找子串第一次出现的位置 |
| `substring(int start[, int end])` | 截取子串（返回 **String**，不影响原对象） |
| `toString()` | **转成真正的 String** |

---

## 六、典型用法示例

```java
StringBuilder sb = new StringBuilder("Hello");

sb.append(" World");          // Hello World
sb.insert(0, ">>> ");         // >>> Hello World
sb.replace(0, 4, "***");      // *** Hello World
sb.delete(0, 4);              // Hello World
sb.reverse();                 // dlroW olleH

String result = sb.toString();
System.out.println(result);
```

---

## 七、和 String、StringBuffer 的区别（高频面试题）

| | String | StringBuilder | StringBuffer |
|---|---|---|---|
| 可变性 | ❌ 不可变 | ✅ 可变 | ✅ 可变 |
| 线程安全 | ✅ 安全（因为不可变） | ❌ 不安全 | ✅ 安全（方法加了 `synchronized`） |
| 性能 | 拼接时最慢 | **最快** | 比 StringBuilder 慢 |
| 使用场景 | 内容不变、少量拼接 | **单线程下的字符串拼接（最常用）** | 多线程下需要拼接 |

**口诀：单线程用 StringBuilder，多线程用 StringBuffer，不变用 String。**

---

## 八、链式调用示例

```java
String result = new StringBuilder()
        .append("hello ")    // 同一个 sb，追加 "hello "
        .append("world")     // 同一个 sb，追加 "world"   →  "hello world"
        .reverse()           // 同一个 sb，反转           →  "dlrow olleh"
        .toString();         // 转成 String 赋值给 result
```

之所以能这样一路 `.` 下去，就是因为 `append` / `reverse` 这些方法**返回的都是 `this`（同一个 StringBuilder 对象）**——这就是它支持链式调用的本质。
