package com.summary._01_inetaddress;

/**
 * 拓展说明：端口 & 协议
 *
 * 一、端口号
 *  - 共 65536 个 (0 ~ 65535)
 *  - 0 ~ 1023：系统保留端口（HTTP 80、HTTPS 443、SSH 22、MySQL 3306 ...）
 *  - 1024 ~ 65535：用户/动态端口
 *  - 同一台机器，同一时刻同一协议下端口号唯一
 *
 * 二、UDP vs TCP
 *  UDP：
 *    - 面向无连接，发送方不管接收方是否就绪
 *    - 不可靠，速度快，单包最大 64KB
 *    - 典型场景：直播、视频会议、游戏帧同步、DNS 查询
 *  TCP：
 *    - 面向连接，三次握手建立、四次挥手断开
 *    - 可靠（顺序、重传、流量控制、拥塞控制）
 *    - 典型场景：HTTP、FTP、文件上传/下载、数据库连接
 *
 * 三、Socket 套接字
 *  - Socket = IP + 端口，是网络通信的端点
 *  - UDP 使用 DatagramSocket / DatagramPacket
 *  - TCP 使用 Socket / ServerSocket
 */
public class PortAndProtocolNote {
    // 仅作笔记，不需要 main 方法
}
