# 字节流 vs 字符流的工程应用

## 一、字节流（InputStream / OutputStream）—— 处理"原始二进制"

工程中的主战场是 **不关心内容是什么、只关心搬运字节** 的场景：

1. **文件上传 / 下载**
   Spring 的 `MultipartFile.getInputStream()`、`HttpServletResponse.getOutputStream()` 写图片 / PDF / Excel / zip 等附件，绝大多数 Web 文件接口都是字节流。

2. **图片、音视频、压缩包**
   拷贝、转储 OSS / S3 / MinIO、ffmpeg 输入输出管道，全是字节流。

3. **网络通信**
   Socket、HTTP body、gRPC、消息队列（Kafka / RabbitMQ payload）底层都是 `byte[]`。

4. **序列化 / 反序列化**
   `ObjectOutputStream`、Protobuf、Kryo、Hessian 的载体必须是字节流。

5. **加解密、压缩**
   `CipherInputStream`、`GZIPOutputStream`、`ZipOutputStream` 都套在字节流之上。

6. **数据库的 BLOB / 文件 IO**
   JDBC `Blob.getBinaryStream()`、NIO `FileChannel`。

> **经验法则**：只要数据可能不是纯文本，就用字节流。这样不会因为某个字节被错误"解码"而损坏文件。

---

## 二、字符流（Reader / Writer）—— 处理"人类可读文本"

字符流 = 字节流 + 字符集解码，把搬运字节这件事提升到搬运"字符"：

1. **读写配置 / 业务文本文件**
   `.properties`、`.yml`、`.json`、`.csv`、`.sql`、`.log`、模板文件。

2. **按行解析大文本**
   `BufferedReader.readLine()` 处理日志、CSV、批处理数据；几乎所有"按行流式处理"都走它（包括 `Files.lines()`、Stream API）。

3. **国际化文本**
   必须显式指定 `Charset`（UTF-8 / GBK / GB18030），避免乱码。

4. **打印 / 日志输出**
   `PrintWriter`、Logback / Log4j 的 file appender 内部就是字符流写日志。

5. **HTTP 请求 / 响应里的文本部分**
   `request.getReader()`、JSON / XML / HTML 文本响应（Spring 也是基于字符流封装）。

6. **代码生成 / 模板渲染**
   FreeMarker、Thymeleaf、MyBatis Generator 输出 `.java` / `.html` 等源码文件。

> **经验法则**：只要内容是"给人看"的字符串、并且要按字符或按行处理，就用字符流，并且永远显式指定字符集（避免依赖系统默认）。

---

## 三、对照速查表

| 维度 | 字节流 | 字符流 |
|------|--------|--------|
| 抽象基类 | `InputStream` / `OutputStream` | `Reader` / `Writer` |
| 数据单位 | 8 bit 字节 | 16 bit 字符（解码后） |
| 适用内容 | 二进制 / 任意文件 | **纯文本** |
| 编码处理 | 不涉及，原样搬运 | 必须指定 `Charset`，否则乱码 |
| 常见高级流 | `BufferedInputStream`、`ObjectOutputStream`、`CipherInputStream`、`GZIPOutputStream` | `BufferedReader/Writer`、`PrintWriter`、`InputStreamReader/OutputStreamWriter` |
| 典型场景 | 上传下载、图音视频、网络包、序列化、加解密 | 配置文件、日志、CSV/JSON 解析、模板渲染 |
| 一句话 | "搬字节" | "搬字符 + 处理编码" |

---

## 四、现代工程中的实际选型

- **首选高级封装**
  `java.nio.file.Files`（`readAllBytes` / `readString` / `lines` / `copy`）、Apache Commons IO 的 `IOUtils`、Guava 的 `ByteStreams` / `CharStreams` —— 几乎不需要自己写 `while(read)` 循环。

- **流式处理大文件**
  仍然必须用 `BufferedInputStream` 或 `BufferedReader`，避免 `readAllBytes` OOM。

- **必须缓冲 + 必须 try-with-resources**
  这两条是生产代码的底线。

- **NIO（FileChannel / ByteBuffer / MappedByteBuffer）**
  超大文件、零拷贝、内存映射场景下取代传统字节流。

- **响应式 / 异步**
  WebFlux 的 `DataBuffer`、Netty 的 `ByteBuf` 本质仍是字节流的高性能抽象。

---

## 五、决策树

```
要处理的数据是什么？
├── 二进制（图片 / 视频 / 压缩包 / 序列化字节 / 网络包）
│       └── 用 字节流（InputStream / OutputStream）
│              └── 大文件 → 加 Buffered，或换 NIO FileChannel
│
└── 文本（配置 / 日志 / JSON / CSV / 模板 / 源码）
        └── 用 字符流（Reader / Writer），并显式指定 Charset.UTF_8
               ├── 按行处理 → BufferedReader.readLine() / Files.lines()
               └── 直接全读 → Files.readString(path, UTF_8)
```

---

## 六、一句话总结

**字节流是 IO 的"基础设施"，字符流是字节流之上专门解决"文本 + 编码"问题的子集。**
