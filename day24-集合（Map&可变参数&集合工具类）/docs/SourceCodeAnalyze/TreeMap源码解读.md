# TreeMap 源码解读

## 1. TreeMap 中每一个节点的内部属性（Entry）

- `K key` —— 键
- `V value` —— 值
- `Entry<K,V> left` —— 左子节点
- `Entry<K,V> right` —— 右子节点
- `Entry<K,V> parent` —— 父节点
- `boolean color` —— 节点的颜色

---

## 2. TreeMap 类中要知道的一些成员变量

```java
public class TreeMap<K, V> {

    // 比较器对象
    private final Comparator<? super K> comparator;

    // 根节点
    private transient Entry<K, V> root;

    // 集合的长度
    private transient int size = 0;
}
```

---

## 3. 空参构造

空参构造就是没有传递比较器对象。

```java
public TreeMap() {
    comparator = null;
}
```

---

## 4. 带参构造

带参构造就是传递了比较器对象。

```java
public TreeMap(Comparator<? super K> comparator) {
    this.comparator = comparator;
}
```

---

## 5. 添加元素

```java
public V put(K key, V value) {
    return put(key, value, true);
}
```

**参数说明**

- **参数一**：键
- **参数二**：值
- **参数三** `replaceOld`：当键重复的时候，是否需要覆盖值
  - `true`：覆盖
  - `false`：不覆盖

```java
private V put(K key, V value, boolean replaceOld) {
    // 获取根节点的地址值，赋值给局部变量 t
    Entry<K, V> t = root;
    // 判断根节点是否为 null
    // 如果为 null，表示当前是第一次添加，会把当前要添加的元素当做根节点
    // 如果不为 null，表示当前不是第一次添加，跳过这个判断继续执行下面的代码
    if (t == null) {
        // 方法的底层会创建一个 Entry 对象，把他当做根节点
        addEntryToEmptyMap(key, value);
        // 表示此时没有覆盖任何的元素
        return null;
    }
    // 表示两个元素的键比较之后的结果
    int cmp;
    // 表示当前要添加节点的父节点
    Entry<K, V> parent;

    // 表示当前的比较规则
    // 如果我们是采取默认的自然排序，那么此时 comparator 记录的是 null，cpr 记录的也是 null
    // 如果我们是采取比较器排序方式，那么此时 comparator 记录的就是比较器
    Comparator<? super K> cpr = comparator;
    // 表示判断当前是否有比较器对象
    // 如果传递了比较器对象，就执行 if 里面的代码，此时以比较器的规则为准
    // 如果没有传递比较器对象，就执行 else 里面的代码，此时以自然排序的规则为准
    if (cpr != null) {
        do {
            parent = t;
            cmp = cpr.compare(key, t.key);
            if (cmp < 0)
                t = t.left;
            else if (cmp > 0)
                t = t.right;
            else {
                V oldValue = t.value;
                if (replaceOld || oldValue == null) {
                    t.value = value;
                }
                return oldValue;
            }
        } while (t != null);
    } else {
        // 把键进行强转，强转成 Comparable 类型的
        // 要求：键必须要实现 Comparable 接口，如果没有实现这个接口
        //       此时在强转的时候，就会报错
        Comparable<? super K> k = (Comparable<? super K>) key;
        do {
            // 把根节点当做当前节点的父节点
            parent = t;
            // 调用 compareTo 方法，比较根节点和当前要添加节点的大小关系
            cmp = k.compareTo(t.key);

            if (cmp < 0)
                // 如果比较的结果为负数，那么继续到根节点的左边去找
                t = t.left;
            else if (cmp > 0)
                // 如果比较的结果为正数，那么继续到根节点的右边去找
                t = t.right;
            else {
                // 如果比较的结果为 0，会覆盖
                V oldValue = t.value;
                if (replaceOld || oldValue == null) {
                    t.value = value;
                }
                return oldValue;
            }
        } while (t != null);
    }
    // 就会把当前节点按照指定的规则进行添加
    addEntry(key, value, parent, cmp < 0);
    return null;
}
```

### `addEntry` 方法

```java
private void addEntry(K key, V value, Entry<K, V> parent, boolean addToLeft) {
    Entry<K, V> e = new Entry<>(key, value, parent);
    if (addToLeft)
        parent.left = e;
    else
        parent.right = e;
    // 添加完毕之后，需要按照红黑树的规则进行调整
    fixAfterInsertion(e);
    size++;
    modCount++;
}
```

### `fixAfterInsertion` 方法（红黑树调整）

辅助方法说明：

- `parentOf`：获取 x 的父节点
- `parentOf(parentOf(x))`：获取 x 的爷爷节点
- `leftOf`：获取左子节点

```java
private void fixAfterInsertion(Entry<K, V> x) {
    // 因为红黑树的节点默认就是红色的
    x.color = RED;

    // 按照红黑规则进行调整
    while (x != null && x != root && x.parent.color == RED) {

        // 判断当前节点的父节点是爷爷节点的左子节点还是右子节点
        // 目的：为了获取当前节点的叔叔节点
        if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
            // 表示当前节点的父节点是爷爷节点的左子节点
            // 那么下面就可以用 rightOf 获取到当前节点的叔叔节点
            Entry<K, V> y = rightOf(parentOf(parentOf(x)));
            if (colorOf(y) == RED) {
                // 叔叔节点为红色的处理方案

                // 把父节点设置为黑色
                setColor(parentOf(x), BLACK);
                // 把叔叔节点设置为黑色
                setColor(y, BLACK);
                // 把爷爷节点设置为红色
                setColor(parentOf(parentOf(x)), RED);

                // 把爷爷节点设置为当前节点
                x = parentOf(parentOf(x));
            } else {
                // 叔叔节点为黑色的处理方案

                // 表示判断当前节点是否为父节点的右子节点
                if (x == rightOf(parentOf(x))) {
                    // 表示当前节点是父节点的右子节点
                    x = parentOf(x);
                    // 左旋
                    rotateLeft(x);
                }
                setColor(parentOf(x), BLACK);
                setColor(parentOf(parentOf(x)), RED);
                rotateRight(parentOf(parentOf(x)));
            }
        } else {
            // 表示当前节点的父节点是爷爷节点的右子节点
            // 那么下面就可以用 leftOf 获取到当前节点的叔叔节点
            Entry<K, V> y = leftOf(parentOf(parentOf(x)));
            if (colorOf(y) == RED) {
                setColor(parentOf(x), BLACK);
                setColor(y, BLACK);
                setColor(parentOf(parentOf(x)), RED);
                x = parentOf(parentOf(x));
            } else {
                if (x == leftOf(parentOf(x))) {
                    x = parentOf(x);
                    rotateRight(x);
                }
                setColor(parentOf(x), BLACK);
                setColor(parentOf(parentOf(x)), RED);
                rotateLeft(parentOf(parentOf(x)));
            }
        }
    }

    // 把根节点设置为黑色
    root.color = BLACK;
}
```

---

## 6. 课堂思考问题

### 6.1 TreeMap 添加元素的时候，键是否需要重写 hashCode 和 equals 方法？

此时是不需要重写的。

### 6.2 HashMap 是哈希表结构的（JDK8 开始由数组、链表、红黑树组成），既然有红黑树，HashMap 的键是否需要实现 Comparable 接口或者传递比较器对象呢？

不需要的。

因为在 HashMap 的底层，默认是利用哈希值的大小关系来创建红黑树的。

### 6.3 TreeMap 和 HashMap 谁的效率更高？

- 如果是最坏情况，添加了 8 个元素，这 8 个元素形成了链表，此时 TreeMap 的效率要更高。
- 但是这种情况出现的几率非常的少。
- 一般而言，还是 HashMap 的效率要更高。

### 6.4 你觉得在 Map 集合中，java 会提供一个"如果键重复了，不会覆盖"的 put 方法吗？

此时 `putIfAbsent` 本身不重要。

> 传递一个思想：
>
> 代码中的逻辑都有两面性，如果我们只知道了其中的 A 面，而且代码中还发现了有变量可以控制两面性的发生，那么该逻辑一定会有 B 面。

**习惯**

- `boolean` 类型的变量控制：一般只有 A、B 两面，因为 `boolean` 只有两个值。
- `int` 类型的变量控制：一般至少有三面，因为 `int` 可以取多个值。

### 6.5 三种双列集合，以后如何选择？

`HashMap` / `LinkedHashMap` / `TreeMap`

- 默认：`HashMap`（效率最高）
- 如果要保证存取有序：`LinkedHashMap`
- 如果要进行排序：`TreeMap`
