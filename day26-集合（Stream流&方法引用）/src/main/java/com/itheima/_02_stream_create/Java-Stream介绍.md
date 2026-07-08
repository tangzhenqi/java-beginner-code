# Java Stream 介绍

Stream（流）是 **JDK 8** 引入的一套用于处理集合/数组数据的 API，位于 `java.util.stream` 包下。它的核心思想是把数据看作一条"流水线"，通过一系列操作对数据进行**过滤、转换、聚合**，让代码更简洁、可读、易并行。

---

## 一、Stream 是什么

可以把 Stream 理解为一条**数据流水线**：

```
数据源  →  中间操作（流水线加工）  →  终结操作（得到结果）
```

几个关键特点：

| 特点 | 说明 |
|------|------|
| **不存储数据** | Stream 不是集合，它只是对数据源的"视图" |
| **不修改源数据** | 操作返回新的 Stream，原集合不变 |
| **惰性求值** | 中间操作不会立即执行，只有遇到终结操作才执行 |
| **一次性** | Stream 用完就废，不能复用 |
| **支持并行** | `parallelStream()` 可以轻松实现并行处理 |

---

## 二、获取 Stream 的方式

参考示例 [StreamFromCollectionDemo.java](src/main/java/com/itheima/_02_stream_create/StreamFromCollectionDemo.java)：

```java
// 1. 单列集合（List/Set/Queue）
list.stream();

// 2. 双列集合 Map（需要先转 entrySet/keySet/values）
map.entrySet().stream();

// 3. 数组
Arrays.stream(arr);
Stream.of(1, 2, 3);

// 4. 函数式生成（无限流）
Stream.generate(() -> Math.random());
Stream.iterate(0, n -> n + 2);
```

示例代码：

```java
package com.itheima._02_stream_create;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Stream;

/**
 * 获取 Stream 的方式 1：单列集合 Collection.stream()
 * 所有实现 Collection 接口的类（List/Set/Queue ...）都有默认方法 stream()，
 * 返回一条「流水线」。Stream 是 JDK8 加在 Collection 上的 default 方法。
 */
public class StreamFromCollectionDemo {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "a", "b", "c", "d", "e");

        // 链式：拿流 -> 遍历
        list.stream().forEach(s -> System.out.println(s));

        // Set 同理
        HashSet<Integer> set = new HashSet<>();
        Collections.addAll(set, 1, 2, 3);
        Stream<Integer> stream = set.stream();
        stream.forEach(System.out::println);
    }
}
```

---

## 三、常见用途

### 1. 过滤（filter）—— 挑出符合条件的元素
```java
list.stream()
    .filter(s -> s.startsWith("a"))
    .forEach(System.out::println);
```

### 2. 映射转换（map）—— 把元素改成另一种形态
```java
list.stream()
    .map(String::toUpperCase)
    .forEach(System.out::println);
```

### 3. 排序（sorted）
```java
list.stream().sorted().forEach(System.out::println);
list.stream().sorted((a, b) -> b - a);  // 自定义
```

### 4. 去重 / 限制数量
```java
list.stream().distinct();      // 去重
list.stream().limit(3);        // 取前 3 个
list.stream().skip(2);         // 跳过前 2 个
```

### 5. 聚合统计
```java
long count = list.stream().count();
int sum = list.stream().mapToInt(Integer::intValue).sum();
OptionalInt max = list.stream().mapToInt(i -> i).max();
```

### 6. 收集结果（collect）—— 最常用
```java
List<String> result = list.stream()
    .filter(s -> s.length() > 2)
    .collect(Collectors.toList());

// 分组
Map<Integer, List<String>> grouped = list.stream()
    .collect(Collectors.groupingBy(String::length));

// 拼接
String joined = list.stream().collect(Collectors.joining(","));
```

### 7. 归约（reduce）—— 把流"合并"成一个值
```java
int sum = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
```

### 8. 并行处理
```java
list.parallelStream().forEach(System.out::println);  // 多线程
```

---

## 四、典型使用场景

- **数据筛选/转换**：从大集合中按条件提取数据（替代 for 循环 + if）
- **集合统计**：求和、计数、平均值、分组
- **数据格式转换**：`List<User>` → `List<String>` → `Map<id, User>`
- **报表/聚合**：按某字段分组统计、TopN 查询
- **并行加速**：CPU 密集的集合运算

---

## 五、传统写法 vs Stream 写法对比

**需求：找出长度大于 3 的字符串，转成大写，去重后收集成 List**

```java
// 传统写法
List<String> result = new ArrayList<>();
Set<String> seen = new HashSet<>();
for (String s : list) {
    if (s.length() > 3) {
        String upper = s.toUpperCase();
        if (seen.add(upper)) {
            result.add(upper);
        }
    }
}

// Stream 写法
List<String> result = list.stream()
    .filter(s -> s.length() > 3)
    .map(String::toUpperCase)
    .distinct()
    .collect(Collectors.toList());
```

可以看到 Stream 写法**更接近自然语言**，意图一目了然。

---

## 六、小结

Stream 不是为了替代集合，而是为了**更优雅地操作集合**。掌握它的核心套路就是：

> **数据源 → filter / map / sorted / distinct（中间操作）→ collect / forEach / count / reduce（终结操作）**

继续看 `day26-summary` 里的其他示例（`_02_stream_create` 后应该还有 `_03_stream_middle`、`_04_stream_terminal` 之类的包），就能逐步掌握完整的 Stream 体系。
