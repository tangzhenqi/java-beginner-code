package com.itheima._02_exception_handle;

/**
 * 知识点 6（拓展）：finally 块
 *
 *   - finally 中的代码"无论是否发生异常、是否被捕获"都会执行。
 *   - 常用于释放资源（IO 流、数据库连接、锁）。
 *   - 经典陷阱：finally 中的 return 会"覆盖"try/catch 中的 return → 强烈不建议在 finally 中 return。
 *   - JDK 7 起更推荐使用 try-with-resources（详见 _11_ext_try_with_resources 模块）。
 */
public class B04_Finally {
    public static void main(String[] args) {
        System.out.println("情景1 正常：" + demo(0));
        System.out.println("情景2 异常：" + demo(-1));
        System.out.println("情景3 陷阱：" + finallyOverride());
    }

    private static String demo(int n) {
        try {
            if (n < 0) throw new IllegalArgumentException("n<0");
            return "try-ok";
        } catch (Exception e) {
            return "catch-handled(" + e.getMessage() + ")";
        } finally {
            System.out.println("  >> finally 必然执行");
        }
    }

    /** 陷阱演示：finally 中 return 会"吞掉"try 中的返回值。 */
    @SuppressWarnings("finally")
    private static int finallyOverride() {
        try {
            return 1;
        } finally {
            return 2; // 实际返回 2 —— 反模式
        }
    }
}
