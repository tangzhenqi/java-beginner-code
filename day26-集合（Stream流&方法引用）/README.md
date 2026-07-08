# day26 知识点归纳与拓展（Stream 流 & 方法引用）

本模块对 day26 三个主题进行整理与适度拓展：

1. **不可变集合**：`List.of` / `Set.of` / `Map.of` / `Map.ofEntries`
2. **Stream 流**：获取流的 4 种方式 + 中间方法 + 终结方法
3. **方法引用**：5 种形式（静态、对象、构造、类名+成员、数组构造）

每个 `.java` 都自带 `main` 方法可独立运行，注释说明核心思想与易错点。

## 目录结构

```
src/main/java/com/itheima/
├── _01_immutable/                       JDK9 不可变集合
│   ├── ImmutableListDemo.java                 List.of 只读、不允许 null
│   ├── ImmutableSetDemo.java                  Set.of 元素必须唯一
│   ├── ImmutableMapDemo.java                  Map.of K-V 对，最多 10 对
│   └── ImmutableMapEntriesDemo.java           Map.ofEntries 超过 10 对的写法
├── _02_stream_create/                   获取 Stream 的方式
│   ├── StreamFromCollectionDemo.java          单列集合 .stream()
│   ├── StreamFromMapDemo.java                 双列：keySet / values / entrySet
│   ├── StreamFromArrayDemo.java               Arrays.stream + IntStream 拓展
│   ├── StreamOfDemo.java                      Stream.of 零散数据（含基本类型数组坑）
│   └── StreamGenerateIterateDemo.java         拓展：iterate / generate 无限流
├── _03_stream_intermediate/             中间方法（返回新 Stream，可链式）
│   ├── StreamFilterDemo.java                  filter 过滤
│   ├── StreamLimitSkipDemo.java               limit / skip 截取
│   ├── StreamDistinctConcatDemo.java          distinct / Stream.concat
│   ├── StreamMapDemo.java                     map 一对一转换
│   ├── StreamSortedDemo.java                  拓展：sorted 自然 / 自定义排序
│   ├── StreamPeekDemo.java                    拓展：peek 调试
│   └── StreamFlatMapDemo.java                 拓展：flatMap 摊平嵌套集合
├── _04_stream_terminal/                 终结方法（一旦调用，流即关闭）
│   ├── StreamForEachCountDemo.java            forEach / count
│   ├── StreamToArrayDemo.java                 toArray（含 String[]::new）
│   ├── StreamCollectListSetDemo.java          Collectors.toList / toSet
│   ├── StreamCollectMapDemo.java              Collectors.toMap（含键冲突处理）
│   ├── StreamCollectGroupingByDemo.java       拓展：groupingBy / partitioningBy
│   ├── StreamCollectJoiningDemo.java          拓展：joining 字符串拼接
│   ├── StreamReduceDemo.java                  拓展：reduce 归约
│   └── StreamMinMaxFindMatchDemo.java         拓展：min/max/find/anyMatch...
├── _05_method_reference/                方法引用 5 种形式
│   ├── Student.java                           演示实体：含单参字符串构造
│   ├── StringOperation.java                   提供静态方法和成员方法
│   ├── MRStaticDemo.java                      ① 类名::静态方法
│   ├── MRInstanceObjectDemo.java              ② 对象::成员方法 + this::
│   ├── MRConstructorDemo.java                 ③ 类名::new
│   ├── MRClassInstanceDemo.java               ④ 类名::成员方法（最易混）
│   ├── MRArrayConstructorDemo.java            ⑤ 类型[]::new
│   └── MRRulesSummaryDemo.java                总结：5 种形式 + 4 条规则
└── _06_practice/                        综合练习
    ├── Actor.java                              练习实体
    ├── PracticeFilterCollectDemo.java          合并 + 过滤 + 排序 + 收集
    ├── PracticeWordCountDemo.java              flatMap + groupingBy(counting)
    ├── PracticeGroupActorDemo.java             按年龄段分组 + 求平均
    └── PracticeMethodReferenceDemo.java        一段流水线集齐多种方法引用
```

## 核心结论速查

### Stream 流水线三段式
```
源（集合 / 数组 / 零散数据 / 无限流）
    ↓ 0..N 个中间方法（filter / map / sorted / distinct / limit / skip / flatMap / peek）
最终调用 1 个终结方法（forEach / count / toArray / collect / reduce / min / max / findFirst / xxxMatch）
```

- 中间方法返回新流，原流不能再用 —— 推荐链式编程。
- 终结方法触发实际计算，没有终结方法的流不会执行（惰性）。
- 流的操作**不影响**原集合 / 原数组。

### 方法引用 4 条通用规则
1. 必须有函数式接口作为上下文。
2. 被引用方法必须已存在。
3. 形参 / 返回值需与抽象方法匹配。
4. 语义上能完成当前需求。

### 「对象::方法」 vs 「类名::方法」
- 调用者在外面已经确定 → 写「对象::方法」（含 `this::` / `super::`）
- 调用者是流中元素本身 → 写「类名::方法」
