# Java 中常见的不可变对象（Immutable Objects）

> 不可变对象指的是一旦创建，其内部状态就不能再被修改的对象。

---

## 一、基本包装类（Wrapper Classes）

`java.lang` 包下的所有包装类都是不可变的：

- `Integer`、`Long`、`Short`、`Byte`
- `Float`、`Double`
- `Character`
- `Boolean`

```java
Integer a = 10;
a = a + 5; // 实际上是创建了一个新的 Integer 对象，原对象未被修改
```

---

## 二、字符串相关

- **`String`** —— 最经典的不可变对象。任何看似修改的操作（如 `substring`、`replace`、`concat`）都会返回新对象。

```java
String s = "hello";
s.toUpperCase(); // 返回新字符串 "HELLO"，s 本身不变
```

> 注意：`StringBuilder` 和 `StringBuffer` 是**可变的**，常被用来作为 `String` 的对比。

---

## 三、大数字类型

- `BigInteger`
- `BigDecimal`

这两个类用于高精度计算，所有运算方法都返回新对象。

```java
BigDecimal d1 = new BigDecimal("1.5");
BigDecimal d2 = d1.add(new BigDecimal("0.5")); // d1 不变，返回新对象
```

---

## 四、Java 8+ 日期时间 API（`java.time`）

Java 8 引入的新日期时间 API，**全部设计为不可变**，这也是它取代旧 `Date`/`Calendar`（可变）的重要原因之一：

- `LocalDate`、`LocalTime`、`LocalDateTime`
- `Instant`
- `Duration`、`Period`
- `ZonedDateTime`、`OffsetDateTime`
- `Year`、`Month`、`DayOfWeek` 等

```java
LocalDate today = LocalDate.now();
LocalDate tomorrow = today.plusDays(1); // today 不变
```

---

## 五、枚举（Enum）

所有 `enum` 类型本质上都是不可变的（每个枚举常量都是单例且状态不可变，前提是不在枚举中放入可变字段）。

---

## 六、其他常见不可变类型

- **`java.util.UUID`**
- **`java.io.File`**（路径不可变）
- **`java.net.InetAddress`**
- **`java.util.Locale`**（基本不可变）
- **不可变集合**：
  - `Collections.unmodifiableList/Set/Map(...)` 返回的视图
  - Java 9+ 的 `List.of()`、`Set.of()`、`Map.of()`
  - Guava 的 `ImmutableList`、`ImmutableMap` 等

```java
List<String> list = List.of("a", "b", "c");
list.add("d"); // 抛出 UnsupportedOperationException
```

---

## 不可变对象的优点

- **线程安全**：无需同步即可在多线程间共享
- **可作为键值**：可以安全地用作 `Map` 的键或 `Set` 的元素
- **便于缓存和复用**：如 `Integer` 缓存池
- **避免意外修改**：减少因状态被修改导致的 bug

---

## 如何自定义不可变类

实现一个不可变类通常遵循以下原则：

1. 类用 `final` 修饰（防止继承）
2. 所有字段用 `private final` 修饰
3. 不提供任何 setter 方法
4. 如果字段是可变对象，则在构造方法和 getter 中做**防御性拷贝**

```java
public final class ImmutablePoint {
    private final int x;
    private final int y;

    public ImmutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }
}
```
