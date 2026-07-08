package com.summary.day15.interfacekw;

/**
 * 接口私有方法（JDK 9 引入）——拓展知识点。
 *
 *  仓库的 Maven 配置当前是 JDK 1.8，所以本文件中"真正的 private 方法"以注释方式给出，
 *  不参与编译；下面的可运行示例用 static + 私有外部类替代，演示"对外只暴露 info/warn，
 *  内部细节封装起来"这一相同的设计目的。
 *
 *  ============== JDK 9 起的真实写法（仅作展示，未编译） ==============
 *  public interface Logger9 {
 *      static void info(String msg) { log("INFO ", msg); }
 *      static void warn(String msg) { log("WARN ", msg); }
 *
 *      // 私有静态方法：仅给 info/warn 复用，外部不可见
 *      private static void log(String level, String msg) {
 *          System.out.println("[" + level + "] " + msg);
 *      }
 *
 *      // 默认方法
 *      default void audit(String action) { write("AUDIT " + action); }
 *
 *      // 私有实例方法：仅给 default 复用
 *      private void write(String line) {
 *          System.out.println(">>> " + line);
 *      }
 *  }
 *  ==================================================================
 *
 *  作用：把多个 default / static 方法中"重复"的代码抽出来，供接口内部复用，
 *       但又不希望对外暴露（实现类、外部代码都看不到）。
 */
public class _06_PrivateMethodDemo {

    public static void main(String[] args) {
        // 1) 接口名调用静态方法 → 内部委托给"包私有"工具类，模拟 JDK 9 的 private static
        Logger.info("启动完成");
        Logger.warn("磁盘占用 90%");

        // 2) 实现类调用默认方法 → 内部委托给同样的工具方法
        Logger l = new ConsoleLogger();
        l.audit("用户登录");
        l.audit("用户登出");
    }
}

interface Logger {

    /** JDK 8 兼容：静态方法 */
    static void info(String msg) {
        LoggerSupport.log("INFO ", msg);   // 模拟"私有静态"复用
    }

    static void warn(String msg) {
        LoggerSupport.log("WARN ", msg);
    }

    /** JDK 8 兼容：默认方法 */
    default void audit(String action) {
        LoggerSupport.log("AUDIT", action);
    }
}

/**
 * 包私有的辅助类，对包外不可见。
 * 这是 JDK 8 模拟 "JDK 9 接口私有方法" 的常见手法。
 */
final class LoggerSupport {
    private LoggerSupport() { }
    static void log(String level, String msg) {
        System.out.println("[" + level + "] " + msg);
    }
}

class ConsoleLogger implements Logger { }
