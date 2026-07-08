package com.summary._01_inetaddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 知识点 1：InetAddress —— IP 地址对象
 *
 * 网络三要素：
 *  1. IP 地址：设备在网络中的唯一标识（IPv4 / IPv6）
 *  2. 端口号：应用程序的唯一标识（0~65535，0~1023 为系统保留端口）
 *  3. 协议：数据传输的规则（UDP / TCP）
 *
 * 常用 API：
 *  static InetAddress getByName(String host)   通过主机名/IP 获取对象
 *  static InetAddress getLocalHost()           获取本机对象
 *  String getHostName()                        获取主机名
 *  String getHostAddress()                     获取 IP 字符串
 *  boolean isReachable(int timeout)            判断是否能 ping 通
 */
public class InetAddressDemo {
    public static void main(String[] args) throws UnknownHostException {
        // 1. 通过 IP 字符串获取对象
        InetAddress byIp = InetAddress.getByName("127.0.0.1");
        System.out.println("HostName = " + byIp.getHostName());
        System.out.println("HostAddress = " + byIp.getHostAddress());

        // 2. 通过域名获取对象（拓展：DNS 解析）
        try {
            InetAddress byDomain = InetAddress.getByName("www.baidu.com");
            System.out.println("baidu IP = " + byDomain.getHostAddress());
        } catch (UnknownHostException e) {
            System.out.println("DNS 解析失败：" + e.getMessage());
        }

        // 3. 获取本机对象
        InetAddress local = InetAddress.getLocalHost();
        System.out.println("本机主机名 = " + local.getHostName());
        System.out.println("本机 IP    = " + local.getHostAddress());

        // 4. 一个域名可能对应多个 IP（CDN/集群常见）
        InetAddress[] all = InetAddress.getAllByName("www.baidu.com");
        for (InetAddress ia : all) {
            System.out.println("解析到：" + ia.getHostAddress());
        }
    }
}
