package com.itheima._04_exception_custom;

/**
 * 知识点 10（拓展）：异常链 (Caused by)
 *
 *   重新抛异常时，把"原始异常"作为 cause 传入：
 *       throw new MyBizException("外层语义", originalException);
 *
 *   好处：
 *     1) 上层只关心"业务上的语义异常"，不必感知底层细节。
 *     2) 同时保留底层的栈轨迹（printStackTrace 会输出 "Caused by:" 一节），定位仍然方便。
 *
 *   反模式：
 *     catch (IOException e) { throw new MyBizException("读取失败"); } // 丢掉了 cause → 调试地狱
 */
public class D02_ExceptionChaining {
    public static void main(String[] args) {
        try {
            loadConfig("not-exist.conf");
        } catch (ConfigLoadException e) {
            System.out.println("业务层捕获: " + e.getMessage());
            System.out.println("根因: " + e.getCause());
            e.printStackTrace(); // 观察 "Caused by: ..." 一节
        }
    }

    private static void loadConfig(String path) {
        try {
            // 模拟底层抛出 IO 类异常
            throw new RuntimeException("文件未找到: " + path);
        } catch (RuntimeException raw) {
            // 包装成业务语义的异常，并把 raw 作为 cause 传进去
            throw new ConfigLoadException("加载配置失败: " + path, raw);
        }
    }

    static class ConfigLoadException extends RuntimeException {
        public ConfigLoadException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
