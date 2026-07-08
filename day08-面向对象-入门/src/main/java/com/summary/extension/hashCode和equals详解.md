# hashCode 和 equals 详解

## 一、它们是什么

两个方法都定义在 `Object` 类中，**所有 Java 类都继承了它们**。

### `equals(Object obj)`
判断**两个对象是否"相等"**。

```java
// Object 默认实现（看源码）
public boolean equals(Object obj) {
    return (this == obj);   // 默认就是比较内存地址
}
```

### `hashCode()`
返回对象的**哈希码**（一个 int 整数），相当于对象的"身份证号"。

```java
public native int hashCode();  // 默认由 JVM 根据内存地址生成
```

---

## 二、为什么需要重写 equals

默认的 `equals` 比较的是**地址**，但业务里我们通常关心**内容**是否相同。

```java
Student s1 = new Student("张三", 18);
Student s2 = new Student("张三", 18);

System.out.println(s1 == s2);        // false（不同对象）
System.out.println(s1.equals(s2));   // false（默认行为，看地址）
```

但从业务角度，s1 和 s2 应该算"同一个学生"。所以要**重写 equals**：

```java
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Student)) return false;
    Student s = (Student) o;
    return age == s.age && Objects.equals(name, s.name);
}
```

---

## 三、为什么重写 equals 必须重写 hashCode

这是 Java 的**铁律**，源于一条契约：

> **两个对象 equals 相等，hashCode 必须相等。**

如果不遵守，**HashMap、HashSet 会出 bug**。

### 举个例子：HashSet 去重失败

```java
Set<Student> set = new HashSet<>();
set.add(new Student("张三", 18));
set.add(new Student("张三", 18));

System.out.println(set.size());  // 期望 1，实际是 2 ❌
```

**为什么？** HashSet 存元素的流程：

1. 先算 `hashCode()` → 决定放到哪个"桶"里
2. 同一桶里再用 `equals()` 比内容

两个对象虽然 equals 相等，但 hashCode 不同 → 被放到不同的桶 → 永远不会触发 equals 比较 → 去重失败。

**正确做法**：

```java
@Override
public int hashCode() {
    return Objects.hash(name, age);
}
```

---

## 四、为什么这么常用

因为 Java 的核心数据结构**全都依赖它们**：

| 场景 | 依赖的方法 |
|---|---|
| `list.contains(obj)` | equals |
| `list.remove(obj)` | equals |
| `HashMap` 的 key | hashCode + equals |
| `HashSet` 去重 | hashCode + equals |
| 字符串比较 `str.equals()` | equals |
| 缓存 key 查找 | hashCode + equals |

只要你用集合、用 Map、做去重、做查找——背后全是它们在工作。

---

## 五、口诀

> **"重写 equals，必重写 hashCode；equals 比内容，hashCode 定位桶。"**

实际开发中直接用 IDE 自动生成，或者用 Lombok 的 `@EqualsAndHashCode` 注解一键搞定。
