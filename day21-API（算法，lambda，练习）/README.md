# day21 知识点归纳与拓展

本模块对 day21 的 **排序算法**、**递归**、**查找算法** 进行整理，并补充了原代码文件夹名中提到但未实现的 **Lambda 表达式** 与常见 **练习**。

## 目录结构

```
src/main/java/com/itheima/
├── _01_sort/                排序算法
│   ├── BubbleSortDemo.java         冒泡排序：相邻两两比较，大的右移
│   ├── SelectionSortDemo.java      选择排序：i 与后面所有元素比较，小的前移
│   ├── InsertionSortDemo.java      插入排序：把无序部分依次插入有序序列
│   ├── QuickSortDemo.java          快速排序：基准数归位 + 递归左右两侧
│   ├── MergeSortDemo.java          拓展：归并排序（分治 + 合并）
│   └── ArraysSortDemo.java         拓展：Arrays.sort 工具方法
├── _02_search/              查找算法
│   ├── BasicSearchDemo.java        基本查找/顺序查找
│   ├── BasicSearchMultiIndexDemo.java 练习：返回所有匹配的索引
│   ├── BinarySearchDemo.java       二分查找/折半查找
│   ├── BinarySearchRecursionDemo.java 拓展：递归实现二分查找
│   ├── Block.java                  分块查找用的 JavaBean
│   ├── BlockSearchDemo.java        分块查找：块内无序，块间有序
│   └── ArraysBinarySearchDemo.java 拓展：Arrays.binarySearch 工具方法
├── _03_recursion/           递归
│   ├── RecursionConceptDemo.java   递归概念、出口、栈溢出
│   ├── RecursionSumDemo.java       递归求 1~n 的和
│   ├── RecursionFactorialDemo.java 递归求阶乘
│   ├── RecursionFibonacciDemo.java 拓展：斐波那契数列（含记忆化）
│   └── RecursionFileSearchDemo.java 拓展：递归遍历文件夹
├── _04_lambda/              拓展：Lambda 表达式
│   ├── FunctionalInterfaceDemo.java   函数式接口与 @FunctionalInterface
│   ├── LambdaBasicDemo.java           匿名内部类 → Lambda 基本写法
│   ├── LambdaShortenDemo.java         Lambda 简化规则
│   ├── LambdaSortDemo.java            Arrays.sort + Comparator + Lambda
│   ├── MethodReferenceDemo.java       方法引用 ::
│   └── CommonFunctionalInterfaceDemo.java JDK8 常用函数式接口
└── _05_practice/            练习
    ├── PracticeSortStudent.java       按学生年龄排序（含 Student JavaBean）
    ├── PracticeBinarySearchInsert.java 二分查找的插入位置
    └── PracticeAllInOne.java          排序 + 查找 + Lambda 综合
```

每个文件都包含 main 方法可独立运行，注释说明核心思想与易错点。
