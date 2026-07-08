# Collections.unmodifiableMap 源码笔记

## 一、问题：选中的 `UnmodifiableMap` 是 `unmodifiableMap()` 的源码吗？

**是，但要分清两个层面：**

- 选中的 `UnmodifiableMap` 是 `Collections.unmodifiableMap()` **返回对象所属的类**；
- 它**不是**那个工厂方法本身。

两者关系：

```java
// 工厂方法
public static <K,V> Map<K,V> unmodifiableMap(Map<? extends K, ? extends V> m) {
    return new UnmodifiableMap<>(m);   // ← 创建并返回 UnmodifiableMap 实例
}

// 返回的类（被选中的源码）
private static class UnmodifiableMap<K, V> implements Map<K, V>, Serializable {
    ...
}
```

## 二、工作原理：装饰器模式（Decorator）

核心思路：包一层原始 Map，读操作放行，写操作拦截。

### 1. 持有原 Map 的引用

```java
private final Map<? extends K, ? extends V> m;
```

### 2. 读操作直接转发（只读，安全）

```java
public V get(Object var1)               { return this.m.get(var1); }
public int size()                       { return this.m.size(); }
public boolean containsKey(Object var1) { return this.m.containsKey(var1); }
```

### 3. 写操作全部抛异常（“不可变”的实现方式）

```java
public V put(K var1, V var2)  { throw new UnsupportedOperationException(); }
public V remove(Object var1)  { throw new UnsupportedOperationException(); }
public void clear()           { throw new UnsupportedOperationException(); }
```

### 4. 视图（keySet / entrySet / values）也包一层只读

否则别人可以通过 `map.keySet().remove(...)` 绕过限制。

```java
public Set<K> keySet() {
    if (this.keySet == null) {
        this.keySet = Collections.unmodifiableSet(this.m.keySet());
    }
    return this.keySet;
}
```

## 三、常见误区：它不是真正的不可变

它只禁止**通过包装对象**修改。若仍持有原始 map 引用，修改原始 map 会反映到包装对象上：

```java
Map<String,Integer> raw  = new HashMap<>();
Map<String,Integer> view = Collections.unmodifiableMap(raw);

raw.put("a", 1);                    // ✅ 可以，改的是 raw
System.out.println(view.get("a"));  // 输出 1，view 跟着变

view.put("b", 2);                   // ❌ 抛 UnsupportedOperationException
```

## 四、想要真正不可变的 Map

使用 Java 9+ 的工厂方法：

- `Map.of(...)`
- `Map.copyOf(...)`

它们创建的是真正独立、不可变的 Map。

---

# 附：什么是装饰器模式（Decorator Pattern）

一种**在不修改原对象代码的前提下，给对象动态增加 / 改变功能**的设计模式。核心做法就是 `UnmodifiableMap` 用的：**包一层**。

## 一句话本质

> 我和你实现同一个接口，我内部持有你（被包装对象）。调用我的方法时，我可以：直接转发给你、在转发前后加点料、或者干脆拦截不让你做。

## 三个关键特征

```java
class UnmodifiableMap<K,V> implements Map<K,V> {   // ① 和被包装对象实现同一接口
    private final Map<K,V> m;                       // ② 内部持有被包装对象

    public V get(Object k) { return m.get(k); }     // ③a 转发：原样放行
    public V put(K k,V v)  { throw new ...; }        // ③b 增强/改变：拦截掉写操作
}
```

- **① 同一接口**：装饰后的对象在外界看来还是个 `Map`，可随意替换，调用方无感知。
- **② 持有原对象**：装饰器内部包着真正干活的对象。
- **③ 拦截 / 增强**：在转发基础上做手脚——`unmodifiableMap` 是“拦截写操作”，别的装饰器可能是“加日志”“加缓存”“加权限校验”。

## 直观类比：点咖啡

```
咖啡             → 10 元
咖啡 + 加奶       → 12 元   （加奶是一层装饰）
咖啡 + 加奶 + 加糖 → 13 元   （加糖又是一层装饰）
```

每加一层，外界看到的“还是一杯咖啡”（同一接口），但功能 / 价格被增强了，而且**可以层层嵌套**——这正是装饰器比直接改源码灵活的地方。

## Java 标准库经典例子：IO 流

IO 流是教科书级的装饰器嵌套：

```java
InputStream in =
    new BufferedInputStream(         // 装饰：加缓冲
        new FileInputStream("a.txt") // 被包装的核心对象
    );

// 还能再包
DataInputStream din =
    new DataInputStream(             // 装饰：能读 int/double 等类型
        new BufferedInputStream(     // 装饰：加缓冲
            new FileInputStream("a.txt")
        )
    );
```

`FileInputStream` 只会从文件读字节，`BufferedInputStream` 给它加缓冲，`DataInputStream` 又加了“读基本类型”的能力——每层都实现 `InputStream` 接口，一层套一层。

## 和继承的区别（为什么不用继承？）

| | 继承 | 装饰器 |
|---|---|---|
| 时机 | 编译期就定死 | **运行时**动态组合 |
| 灵活性 | “缓冲+加密+压缩”要写各种组合类，类爆炸 | 三个装饰器自由嵌套即可 |
| 耦合 | 强耦合父类 | 只依赖接口 |

## 回到 `unmodifiableMap`

它就是一个“只读装饰器”：实现 `Map` 接口、包着原 map、读操作转发、写操作拦截抛异常。所以它能套在 `HashMap`、`TreeMap`、`LinkedHashMap`…… 任何 `Map` 上，这就是装饰器模式“一次编写、装饰万物”的好处。
