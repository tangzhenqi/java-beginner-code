# day27 拓展：File 类

本模块系统化归纳 `java.io.File` 的常用 API，从构造、判断、信息、增删，到遍历与递归综合案例，最后补充两个常用拓展（try-with-resources、NIO.2 的 Path/Files/Files.walk）。

> 独立的 Maven 项目，JDK 11+。
>
> 📌 异常体系相关内容（异常处理 / throws-throw / 自定义异常 / 异常链）见同级模块 **`day27-IO(异常&综合案例）`**。

## 包结构（已扁平化，按知识点循序渐进编号）

所有演示类直接位于 `com.itheima` 包下，文件名前缀 `_01` ~ `_14` 即为学习顺序：

```
src/main/java/com/itheima/
├── _01_FileConstructors    File 构造方法
├── _02_JudgeMethods        exists / isFile / isDirectory
├── _03_InfoMethods         length / getName / getAbsolutePath …
├── _04_CreateAndDelete     createNewFile / mkdirs / delete
├── _05_ListMethods         listRoots / list / listFiles
├── _06_Filters             FileFilter / FilenameFilter
├── _07_FindByExtension     递归找文件
├── _08_FolderSize          递归统计大小
├── _09_DeleteTree          递归删整棵树
├── _10_CountByExtension    递归扩展名计数
├── _11_PrintTree           打印目录树
├── _12_TryWithResources    拓展：自动关闭资源
├── _13_PathsAndFiles       拓展：NIO.2 Path/Files
└── _14_FilesWalk           拓展：Files.walk
```

## 知识点速查表

| 新文件名 | 知识点 |
|---|---|
| `_01_FileConstructors` | File 构造方法 |
| `_02_JudgeMethods` | exists / isFile / isDirectory |
| `_03_InfoMethods` | length / getName / getAbsolutePath … |
| `_04_CreateAndDelete` | createNewFile / mkdirs / delete |
| `_05_ListMethods` | listRoots / list / listFiles |
| `_06_Filters` | FileFilter / FilenameFilter |
| `_07_FindByExtension` | 递归找文件 |
| `_08_FolderSize` | 递归统计大小 |
| `_09_DeleteTree` | 递归删整棵树 |
| `_10_CountByExtension` | 递归扩展名计数 |
| `_11_PrintTree` | 打印目录树 |
| `_12_TryWithResources` | 拓展：自动关闭资源 |
| `_13_PathsAndFiles` | 拓展：NIO.2 Path/Files |
| `_14_FilesWalk` | 拓展：Files.walk |

## 常见踩坑提示

1. **`File.listFiles()` 可能返回 `null`**（路径不存在 / 不是目录 / 无权限），递归前必须判空，否则 NPE。
2. **`File.delete()` 不走回收站**，且对非空目录直接返回 `false`，需要自行递归删除（见 `_09_DeleteTree`）。
3. **`createNewFile` 在父目录不存在时会抛 `IOException`**，而不是返回 false。
4. **`mkdir` vs `mkdirs`**：日常推荐用 `mkdirs`，单级、多级都能创建。
5. **`getAbsolutePath` 受 `user.dir` 影响**：IDE 启动目录可能和命令行不一致。
6. **递归删树务必"先儿子再自己"**：先递归删完子项，最后才能删父目录。

## 运行示例

```bash
# 编译
mvn compile

# 运行任意一个 main（示例：打印目录树）
mvn exec:java -Dexec.mainClass=com.itheima._11_PrintTree

# 或者用 java 直接运行编译产物
java -cp target/classes com.itheima._08_FolderSize
```

> 部分案例会在 `target/playground/` 下创建/删除测试目录，仅用于演示，可放心运行。
