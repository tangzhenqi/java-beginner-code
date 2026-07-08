# day24 知识点归纳与拓展（Map & 可变参数 & 集合工具类）

本模块对 day24 三个主题进行整理与适度拓展：

1. **Map 集合**：Map 接口、HashMap、LinkedHashMap、TreeMap
2. **可变参数**：JDK5 引入的 `类型... 名字` 语法
3. **集合工具类**：`Collections` 中的批量添加、排序、查找、拷贝、最值、交换等

每个 `.java` 都自带 `main` 方法可独立运行，注释说明核心思想与易错点。

## 目录结构

```
src/main/java/com/itheima/
├── _01_map/                Map 接口与基础
│   ├── MapBasicDemo.java                 基本方法：put / get / remove / containsXxx / size ...
│   ├── MapTraverseKeySetDemo.java        遍历方式 1：keySet（增强 for / 迭代器 / lambda）
│   ├── MapTraverseEntrySetDemo.java      遍历方式 2：entrySet（推荐）
│   ├── MapTraverseForEachDemo.java       遍历方式 3：forEach + BiConsumer
│   ├── MapOfImmutableDemo.java           拓展：Collections.unmodifiableMap 只读视图
│   ├── MapGetOrDefaultDemo.java          拓展：getOrDefault / putIfAbsent
│   └── MapMergeDemo.java                 拓展：JDK8 merge 一行写计数器
├── _02_hashmap/            HashMap 实现
│   ├── Student.java                       自定义键：重写 hashCode + equals
│   ├── HashMapBasicDemo.java              特点：无序、允许 null 键 / 值
│   ├── HashMapCustomKeyDemo.java          以自定义对象为键
│   ├── HashMapCountDemo.java              用 Map 统计景点投票（最经典应用）
│   └── HashMapPrincipleDemo.java          拓展：底层原理（数组 + 链表 + 红黑树）
├── _03_linkedhashmap/      LinkedHashMap
│   └── LinkedHashMapDemo.java             插入有序；拓展：accessOrder 实现 LRU
├── _04_treemap/            TreeMap
│   ├── Student.java                       实现 Comparable 指定默认排序
│   ├── TreeMapComparatorDemo.java         构造时传 Comparator 自定义规则
│   ├── TreeMapComparableDemo.java         自定义对象 + Comparable
│   └── TreeMapCountCharDemo.java          字符次数统计并按字典序输出
├── _05_varargs/            可变参数
│   ├── VarArgsOverloadDemo.java           演进 1：方法重载
│   ├── VarArgsArrayDemo.java              演进 2：传数组
│   ├── VarArgsBasicDemo.java              可变参数 int... args
│   ├── VarArgsRulesDemo.java              使用细节（最多一个，必须放最后）
│   └── VarArgsAsListDemo.java             拓展：Arrays.asList / printf 等可变参数 API
├── _06_collections/        Collections 工具类
│   ├── CollectionsAddAllShuffleDemo.java  addAll / shuffle
│   ├── CollectionsSortDemo.java           sort（默认 / Comparator / lambda）
│   ├── CollectionsBinarySearchDemo.java   binarySearch（含未找到返回值的含义）
│   ├── CollectionsCopyFillDemo.java       copy / fill
│   ├── CollectionsMaxMinDemo.java         max / min（含自定义比较器）
│   ├── CollectionsSwapReverseDemo.java    swap / reverse / frequency / replaceAll
│   └── CollectionsUnmodifiableDemo.java   拓展：unmodifiableXxx 只读视图
└── _07_practice/           练习
    ├── PracticeRandomNameDemo.java        随机点名（shuffle）
    ├── PracticeWeightedRandomDemo.java    带权重随机（7:3 男女）
    ├── PracticeNoRepeatRandomDemo.java    不重复点名（轮内不重复）
    └── PracticeProvinceCityDemo.java      Map 值为 List：省份 -> 多个城市
```

## 知识点速查

### 一、Map 三大实现对比

| 实现             | 底层结构             | 是否有序       | 排序方式               |
| ---------------- | -------------------- | -------------- | ---------------------- |
| HashMap          | 数组 + 链表 + 红黑树 | 无序           | 无                     |
| LinkedHashMap    | HashMap + 双向链表   | 有序（插入序）| 维护插入顺序            |
| TreeMap          | 红黑树               | 有序           | 按键自然排序或 Comparator |

### 二、Map 的三种遍历

1. `keySet()` -> 遍历键 -> 用 `map.get(key)` 取值
2. `entrySet()` -> 遍历 `Map.Entry`（推荐）
3. `map.forEach((k, v) -> ...)`（最简洁）

### 三、可变参数三条规则

1. 底层就是数组，Java 自动创建
2. 一个方法最多一个可变参数
3. 可变参数必须写在形参列表最后

### 四、Collections 常用方法

| 方法           | 作用                       |
| -------------- | -------------------------- |
| addAll         | 批量添加                   |
| shuffle        | 随机打乱                   |
| sort           | 排序                       |
| binarySearch   | 二分查找（需先有序）       |
| copy           | 拷贝（注意 dest 大小）     |
| fill           | 全部替换为指定值           |
| max / min      | 最大 / 最小值              |
| swap           | 交换指定位置               |
| reverse        | 反转                       |
| frequency      | 统计出现次数               |

## 运行方式

```bash
# 任意 Demo 都能直接运行，例如：
mvn -q compile exec:java -Dexec.mainClass=com.itheima._01_map.MapBasicDemo
```

或直接在 IDEA 中点击对应类的 `main` 方法运行。
