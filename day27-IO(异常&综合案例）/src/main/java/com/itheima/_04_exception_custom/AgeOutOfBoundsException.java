package com.itheima._04_exception_custom;

/** 自定义运行时异常：年龄越界。 */
public class AgeOutOfBoundsException extends RuntimeException {
    public AgeOutOfBoundsException() {
    }
    public AgeOutOfBoundsException(String message) {
        super(message);
    }
}
