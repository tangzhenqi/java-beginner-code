package com.itheima._12_classloader;

/**
 * 被加载的目标类。
 * 特意保持"自包含"：只用到 java.lang 里的东西（String），
 * 这样它被自定义加载器加载时，不会牵扯到别的业务类，现象更纯粹。
 */
public class Sample {

    public String hello() {
        return "hello from Sample, loaded by " + getClass().getClassLoader();
    }
}
