package com.itheima;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 实战案例：用类加载器读取 db.properties 初始化数据库连接参数
 * <p>
 * 这是框架里最常见的用法：配置文件放在 classpath（resources 目录）下，
 * 通过 ClassLoader.getResourceAsStream 读取，而不用写死绝对路径。
 * 这样打成 jar 部署到任何机器都能正确加载。
 */
public class DbConfigLoader {

    private final String driver;
    private final String url;
    private final String username;
    private final String password;

    public DbConfigLoader() {
        Properties prop = new Properties();
        // 用类加载器加载 classpath 根目录下的 db.properties
        // 编译后 resources 里的文件会被复制到 target/classes，也就是 classpath 根
        ClassLoader classLoader = DbConfigLoader.class.getClassLoader();
        try (InputStream is = classLoader.getResourceAsStream("db.properties")) {
            if (is == null) {
                throw new IllegalStateException("classpath 下找不到 db.properties");
            }
            prop.load(is);
        } catch (IOException e) {
            throw new RuntimeException("读取数据库配置失败", e);
        }

        this.driver = prop.getProperty("driver");
        this.url = prop.getProperty("url");
        this.username = prop.getProperty("username");
        this.password = prop.getProperty("password");
    }

    public void printConfig() {
        System.out.println("==== 从 classpath 加载到的数据库配置 ====");
        System.out.println("driver   = " + driver);
        System.out.println("url      = " + url);
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        System.out.println("（真实项目会用这些参数去 DriverManager.getConnection 建立连接）");
    }

    public static void main(String[] args) {
        new DbConfigLoader().printConfig();
    }
}
