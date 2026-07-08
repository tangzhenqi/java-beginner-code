# day29-IO-summary

Day29「IO（其他流）」知识点归纳总结与拓展。一个独立的 Maven 项目。

## 项目结构

```
day29-IO-summary/
├── pom.xml
├── README.md
├── resources/                       运行时使用的输入/输出文件
└── src/main/java/com/summary/io/
    ├── buffered/                    缓冲流
    │   ├── BufferedByteStreamDemo   字节缓冲流拷贝文件
    │   ├── BufferedCharStreamDemo   字符缓冲流 readLine / newLine
    │   └── BufferedPrincipleDemo    拓展：缓冲流原理与性能对比
    ├── convert/                     转换流
    │   ├── ConvertStreamDemo        InputStreamReader / OutputStreamWriter
    │   └── Jdk11ReplacementDemo     JDK11 起 FileReader/FileWriter 替代方案
    ├── charset/                     字符集
    │   └── CharsetDemo              UTF-8/GBK 编解码与乱码本质
    ├── print/                       打印流
    │   ├── PrintStreamDemo          字节打印流 + 重定向 System.out
    │   └── PrintWriterDemo          字符打印流 + autoFlush
    ├── object/                      对象序列化流
    │   ├── Student                  实现 Serializable 的 POJO
    │   ├── ObjectOutputDemo         单对象 / 集合写出
    │   └── ObjectInputDemo          反序列化读取
    ├── zip/                         压缩 / 解压缩流
    │   ├── ZipDemo                  ZipOutputStream 递归压缩
    │   └── UnzipDemo                ZipInputStream 解压
    └── commonsio/                   第三方工具类
        ├── CommonsIoDemo            Apache Commons-IO
        └── HutoolDemo               Hutool
```

## 知识点总览

### 1. 缓冲流（高级流）
- `BufferedInputStream` / `BufferedOutputStream`：字节缓冲流，内部 8192 字节缓冲区。
- `BufferedReader` / `BufferedWriter`：字符缓冲流，特有方法 `readLine()` / `newLine()`。
- 性能优势：减少系统调用次数（缓冲区命中），相同任务通常快 5-10 倍。

### 2. 转换流
- `InputStreamReader` / `OutputStreamWriter`：字节流 ←→ 字符流桥梁，可指定字符集。
- JDK11 推荐使用 `new FileReader(file, charset)` / `new FileWriter(file, charset)` 直接替代。

### 3. 字符集
- ASCII / GBK / UTF-8 / UTF-16 的字节占用区别。
- `String.getBytes(Charset)` 与 `new String(byte[], Charset)`。
- 乱码本质：编解码字符集不一致。

### 4. 打印流
- `PrintStream`（字节）/ `PrintWriter`（字符）。
- 特有方法：`println` / `print` / `printf`，可开启 `autoFlush`。
- 不抛 `IOException`，通过 `checkError()` 上报；`System.out` 即 `PrintStream`，可重定向。

### 5. 对象操作流（序列化）
- `ObjectOutputStream.writeObject()` / `ObjectInputStream.readObject()`。
- 类必须实现 `Serializable`，建议显式声明 `serialVersionUID`。
- `transient` 字段不参与序列化。
- 多个对象建议用集合包装后一次性写出/读入。

### 6. 压缩流
- `ZipOutputStream` 压缩：`putNextEntry()` → 写数据 → `closeEntry()`。
- `ZipInputStream` 解压：`getNextEntry()` 拿到 `ZipEntry`，根据 `isDirectory()` 决定建目录还是写文件。

### 7. 第三方 IO 工具
- **Commons-IO**：`FileUtils.copyFile` / `readFileToString` / `IOUtils.copy` 等。
- **Hutool**：`FileUtil.copy` / `readUtf8String` / `writeUtf8String`，国内常用。

## 运行方式

```bash
# 进入项目根目录
cd day29-IO-summary

# 编译（首次需要联网下载依赖）
mvn compile

# 运行某个 Demo（示例：缓冲流拷贝）
mvn exec:java -Dexec.mainClass="com.summary.io.buffered.BufferedByteStreamDemo"
```

也可以直接用 IDE 打开该目录，作为 Maven 项目导入后运行每个 main 方法即可。

## 拓展点（相对 day29 原代码的补充）

1. `BufferedPrincipleDemo`：通过 5MB 大文件实测，对比裸 `FileInputStream` 与 `BufferedInputStream` 逐字节拷贝耗时，直观说明缓冲区的价值。
2. `PrintStreamDemo`：演示了 `System.setOut` 把标准输出临时重定向到文件——这是日志/抓控制台输出的常用技巧。
3. `Student`：显式声明了 `serialVersionUID`，并用 `transient` 修饰 `password` 字段，演示敏感字段不参与序列化。
4. `ZipDemo`：在 day29 仅提供解压示例的基础上，补充递归压缩目录的实现，并兼容空目录。
5. 统一使用 `try-with-resources`，自动关流；统一使用相对路径 `resources/`，跨平台兼容（day29 原代码使用了 Windows 路径 `myio\\a.txt` 和 `D:\\` 等绝对路径）。
