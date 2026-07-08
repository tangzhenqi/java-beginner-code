package com.itheima._01_map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 拓展：把一个 Map 包装成"不可变 Map"。
 *
 * 工程实践中，把内部 Map 暴露给外部时，常用 Collections.unmodifiableMap，
 * 这样调用方拿到的是只读视图（修改会抛 UnsupportedOperationException）。
 *
 * （JDK9+ 还提供更简洁的 Map.of / Map.ofEntries，本项目使用 JDK8 故不演示。）
 *
 * 注意：unmodifiableMap 是"视图"，原 Map 修改了视图也会跟着变；
 *      它只是禁止通过返回值这条路径写入。
 */
public class MapOfImmutableDemo {
    public static void main(String[] args) {
        // 构造一个 Map
        HashMap<String, Integer> origin = new HashMap<>();
        origin.put("张三", 18);
        origin.put("李四", 19);
        origin.put("王五", 20);

        // 包装成只读视图
        Map<String, Integer> readonly = Collections.unmodifiableMap(origin);
        System.out.println("只读视图：" + readonly);

        // 通过视图修改 -> 抛异常
        try {
            readonly.put("赵六", 21);
        } catch (UnsupportedOperationException e) {
            System.out.println("修改只读 Map 抛出：" + e.getClass().getSimpleName());
        }

        // 通过原 Map 修改 -> 视图会跟着变
        origin.put("赵六", 21);
        System.out.println("原 Map 改后 视图：" + readonly);
    }
}
