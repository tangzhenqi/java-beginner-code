package com.itheima._04_exception_custom;

/**
 * 自定义运行时异常：姓名格式不合法
 *
 * 命名规范：
 *   - 业务含义 + Exception 结尾，比如 NameFormatException、AgeOutOfBoundsException
 *
 * 选择父类：
 *   - 继承 RuntimeException → 运行时异常，不强制 try-catch（常用于参数校验）
 *   - 继承 Exception        → 编译时异常，调用者必须显式处理（常用于强提醒）
 *
 * 套路：写两个构造（空参 + 带 message），分别调用 super()。
 */
public class NameFormatException extends RuntimeException {
    public NameFormatException() {
    }
    public NameFormatException(String message) {
        super(message);
    }
}
