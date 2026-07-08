# Day33 网络编程 —— 知识点归纳与拓展

本项目是一个独立的 Maven 项目，对 day33 的内容进行结构化整理与拓展，并按知识点拆分到不同的 Java 文件中，方便逐点学习。

## 一、网络编程三要素

| 要素 | 说明 | 对应类 |
| ---- | ---- | ---- |
| IP   | 设备的唯一标识（IPv4 / IPv6） | `InetAddress` |
| 端口 | 应用程序唯一标识（0~65535） | `Socket` / `DatagramSocket` |
| 协议 | 数据传输规则 | `UDP` / `TCP` |

## 二、知识点目录

| 包 | 知识点 | 主要 API |
| --- | --- | --- |
| `_01_inetaddress` | IP 对象 & 网络基础笔记 | `InetAddress` |
| `_02_udp`         | UDP 单次发送/接收      | `DatagramSocket`、`DatagramPacket` |
| `_03_udp_chat`    | UDP 多次发送（聊天）   | 循环 + Scanner |
| `_04_broadcast`   | UDP 广播（255.255.255.255）| `DatagramSocket` |
| `_05_multicast`   | UDP 组播（224.0.0.0 ~ 239.255.255.255）| `MulticastSocket` |
| `_06_tcp`         | TCP 基本通信 + 中文乱码处理 | `Socket`、`ServerSocket` |
| `_07_tcp_echo`    | TCP 双向通信（shutdownOutput）| `socket.shutdownOutput()` |
| `_08_tcp_file`    | TCP 文件上传 + UUID 命名 | 缓冲流 + Socket |
| `_09_multi_thread`| 多线程 TCP 服务器       | `new Thread(...).start()` |
| `_10_thread_pool` | 线程池 TCP 服务器（生产推荐）| `ThreadPoolExecutor` |
| `_11_http`        | 拓展：URL + HttpURLConnection 调用 HTTP | `HttpURLConnection` |
| `_12_nio`         | 拓展：NIO Reactor 回声服务器 | `Selector`、`Channel`、`Buffer` |

## 三、UDP vs TCP

|              | UDP                              | TCP                              |
| ------------ | -------------------------------- | -------------------------------- |
| 连接         | 无连接                            | 面向连接（3 次握手 / 4 次挥手）   |
| 可靠性       | 不可靠（可能丢/乱序/重复）         | 可靠（顺序、重传、流控、拥塞控制） |
| 报文大小     | 单包最大 64KB                     | 流式，无大小限制                   |
| 通信模式     | 单播 / 广播 / 组播                | 一对一                            |
| 典型场景     | 直播、游戏、DNS、视频会议          | HTTP、FTP、文件传输、数据库        |

## 四、TCP 编程要点

1. **创建即连接**：`new Socket(ip,port)` 立即发起 3 次握手；失败抛异常。
2. **accept 阻塞**：服务端 `ss.accept()` 阻塞等待连接。
3. **无 EOF**：TCP 是流式协议，读到 -1 只有"对端关闭"才发生。
4. **shutdownOutput**：客户端发完后调用 `socket.shutdownOutput()`，
   告知服务端"我说完了"，对端 `read()` 才能返回 -1。
5. **中文乱码**：避免对字节流逐字节 `read()` 后强转 char，
   改用字符流 `InputStreamReader/OutputStreamWriter` 并显式指定 UTF-8。
6. **资源释放**：socket 关闭会顺带关掉对应输入输出流。

## 五、并发模型演进

```
单线程        阻塞 accept、阻塞 read，只能服务一个客户端
   ↓
多线程        每个连接一个线程；并发数受限于线程数（OOM 风险）
   ↓
线程池        复用线程，控制资源；推荐 ThreadPoolExecutor 自定义参数
   ↓
NIO / Reactor 单线程 Selector 管理大量连接，事件驱动（Netty 基础）
   ↓
AIO / 异步    系统主动回调（Windows IOCP）
```

`ThreadPoolExecutor` 七大参数：核心线程数、最大线程数、空闲存活时间、时间单位、
任务队列、线程工厂、拒绝策略。**禁止**直接 `Executors.newFixedThreadPool`（队列无界）/
`newCachedThreadPool`（线程无界），生产中要显式指定参数 + 有界队列。

## 六、目录结构

```
day33-网络编程-summary/
├── pom.xml
├── README.md
├── resources/
│   ├── clientdir/   # 客户端上传源目录（放一张 a.jpg 即可测试）
│   └── serverdir/   # 服务端保存目录
└── src/main/java/com/summary/
    ├── _01_inetaddress/
    ├── _02_udp/
    ├── _03_udp_chat/
    ├── _04_broadcast/
    ├── _05_multicast/
    ├── _06_tcp/
    ├── _07_tcp_echo/
    ├── _08_tcp_file/
    ├── _09_multi_thread/
    ├── _10_thread_pool/
    ├── _11_http/
    └── _12_nio/
```

## 七、运行方式

每个 demo 都是独立的 `main` 方法，先启服务端再启客户端即可：

```bash
# 编译
mvn compile

# 例：跑 UDP 单次发送
mvn exec:java -Dexec.mainClass="com.summary._02_udp.UdpReceiver"
mvn exec:java -Dexec.mainClass="com.summary._02_udp.UdpSender"
```

或直接在 IDE 中右键 `Run`。

文件上传测试前，将任意一张图片放到 `resources/clientdir/a.jpg`。
