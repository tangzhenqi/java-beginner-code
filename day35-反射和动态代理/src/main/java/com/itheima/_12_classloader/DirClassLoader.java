package com.itheima._12_classloader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 自定义类加载器：从指定目录读取 .class 文件并加载。
 *
 * 关键点：默认的双亲委派会让加载请求先交给父加载器（应用加载器）。
 * 如果 Sample 在 classpath 上，父加载器就把它加载了，两个自定义加载器
 * 拿回来的会是"同一个" Class —— 演示不出差异。
 *
 * 所以这里重写 loadClass，对"受管前缀"的类【打破双亲委派】，强制由本加载器
 * 自己 findClass -> defineClass，从而每个加载器都持有自己的一份 Class。
 * 其它类（java.lang.* 等）仍然正常委派给父加载器，保证共享、避免出错。
 */
public class DirClassLoader extends ClassLoader {

    /** .class 文件所在的根目录（对应包结构的根，如 target/classes） */
    private final String classDir;

    /** 受本加载器接管、打破双亲委派的类名前缀 */
    private final String managedPrefix;

    public DirClassLoader(String classDir, String managedPrefix) {
        // 父加载器仍设为应用加载器，供委派使用
        super(DirClassLoader.class.getClassLoader());
        this.classDir = classDir;
        this.managedPrefix = managedPrefix;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            // 1) 本加载器是否已经加载过它
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                if (name.startsWith(managedPrefix)) {
                    // 2) 受管类：打破双亲委派，自己加载
                    c = findClass(name);
                } else {
                    // 3) 其它类：走正常双亲委派
                    c = super.loadClass(name, resolve);
                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = readClassBytes(name);
        if (bytes == null) {
            throw new ClassNotFoundException(name);
        }
        // 把字节码"定义"成一个 Class —— 每次调用都会产生一个全新的 Class
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] readClassBytes(String name) {
        // com.itheima._12_classloader.Sample -> com/itheima/_12_classloader/Sample.class
        Path path = Paths.get(classDir, name.replace('.', '/') + ".class");
        if (!Files.exists(path)) {
            return null;
        }
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException("读取字节码失败: " + path, e);
        }
    }
}
