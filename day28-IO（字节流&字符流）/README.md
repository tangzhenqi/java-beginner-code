# day28 IO（字节流 & 字符流）知识点归纳与拓展

## 一、IO 流总览

按 **数据单位** 划分：
- 字节流（8 bit）：可读写任意类型文件（图片、音视频、文本等）
  - `InputStream` / `OutputStream`（抽象基类）
  - `FileInputStream` / `FileOutputStream`（节点流）
  - `BufferedInputStream` / `BufferedOutputStream`（高级流，带缓冲）
  - `ObjectInputStream` / `ObjectOutputStream`（高级流，序列化）
- 字符流（16 bit / 一个字符）：仅适合 **文本文件**，能正确处理中文
  - `Reader` / `Writer`（抽象基类）
  - `FileReader` / `FileWriter`（节点流）
  - `InputStreamReader` / `OutputStreamWriter`（转换流）
  - `BufferedReader` / `BufferedWriter`（高级流，带缓冲，有 readLine/newLine）

按 **流向** 划分：输入流 / 输出流（**站在程序的角度** 看）。

按 **角色** 划分：节点流 / 处理流（高级流）。

## 二、本项目结构

```
src/main/java/com/summary/io
├── Main.java                       入口，依次运行各 Demo
├── bytestream/
│   ├── FileOutputStreamDemo.java   字节输出流：write 的三种重载、续写、换行
│   ├── FileInputStreamDemo.java    字节输入流：单字节、字节数组、循环读
│   ├── FileCopyDemo.java           文件拷贝：单字节 vs 字节数组耗时对比
│   └── TryWithResourcesDemo.java   JDK7 / JDK9 try-with-resources 写法
├── charset/
│   └── CharsetDemo.java            编码 / 解码、UTF-8 vs GBK、为何字节流读中文乱码
├── charstream/
│   ├── FileReaderDemo.java         字符输入流：单字符、字符数组
│   ├── FileWriterDemo.java         字符输出流：写出、flush vs close
│   └── CharStreamCopyDemo.java     字符流拷贝（仅适合文本）
├── convert/
│   └── ConvertStreamDemo.java      InputStreamReader / OutputStreamWriter +
│                                   JDK11 替代：FileReader(path, Charset)
├── buffered/
│   ├── BufferedByteStreamDemo.java 字节缓冲流
│   ├── BufferedCharStreamDemo.java 字符缓冲流（readLine / newLine）
│   └── BufferedCompareDemo.java    四种拷贝方式性能对比（拓展）
├── object/
│   ├── Student.java                序列化实体（serialVersionUID + transient）
│   ├── ObjectOutputDemo.java       序列化单个对象
│   ├── ObjectInputDemo.java        反序列化
│   └── ObjectListDemo.java         拓展：序列化集合
└── advanced/
    ├── ZipDemo.java                拓展：ZipOutputStream 压缩文件夹
    └── PrintStreamDemo.java        拓展：PrintStream / System.out 重定向
```

## 三、知识点速查

| 主题 | 关键点 |
|------|--------|
| FileOutputStream 构造 | 父目录必须存在；文件不存在自动创建；默认覆盖，`append=true` 续写 |
| FileInputStream 构造 | 文件不存在 **直接抛 FileNotFoundException** |
| `read()` 返回值 | 读到末尾返回 `-1`，否则返回字节/字符的十进制 |
| `read(byte[]/char[])` | 返回本次读取的有效长度，**必须用 `0~len` 截取** |
| 换行符 | Windows `\r\n`、Linux `\n`、Mac `\r`，推荐 `BufferedWriter.newLine()` |
| 字符集 | 默认 UTF-8（JDK18+），GBK 一个汉字 2 字节，UTF-8 一个汉字 3 字节 |
| 编码 / 解码 | `str.getBytes(charset)` / `new String(bytes, charset)` |
| 字节流读中文 | 一次读 1 字节，半个汉字 → 乱码（除非缓冲到完整字符再解码） |
| try-with-resources | JDK7：`try(资源声明)`；JDK9：可在 `try()` 中引用 **已声明的 final/effectively final** 资源 |
| 缓冲流原理 | 内部维护 8KB 默认缓冲区，减少系统调用次数 |
| 序列化要求 | 实体类必须 `implements Serializable`；推荐显式声明 `serialVersionUID`；`transient` 字段不参与序列化 |

## 四、运行

```bash
mvn -q -e -DskipTests package
mvn -q exec:java -Dexec.mainClass=com.summary.io.Main
```

或在 IDEA 中直接运行任一 Demo 的 `main` 方法。所有读写均在项目内的 `data/` 目录下进行，避免污染外部路径。
