package com.itheima._04_stream_terminal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 终结方法：collect 到 Map —— Collectors.toMap
 * <p>
 * 形参：
 *   - keyMapper   ：每个元素产生键
 *   - valueMapper ：每个元素产生值
 * 易错点：
 *   - 键不能重复，否则抛 IllegalStateException：Duplicate key
 *   - 解决方法：传第 3 个参数 mergeFunction，决定碰撞时如何取舍。
 */
public class _04_StreamCollectMapDemo {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,
                "张无忌-男-15", "周芷若-女-14", "赵敏-女-13",
                "张强-男-20", "张三丰-男-100");

        // 男性：姓名 -> 年龄
        Map<String, Integer> map = list.stream()
                .filter(s -> "男".equals(s.split("-")[1]))
                .collect(Collectors.toMap(
                        s -> s.split("-")[0],
                        s -> Integer.parseInt(s.split("-")[2])
                ));
        System.out.println(map);

        // 键碰撞演示：把所有人都用「性别」作为键 -> 重复
        try {
            list.stream().collect(Collectors.toMap(
                    s -> s.split("-")[1],
                    s -> s.split("-")[0]
            ));
        } catch (IllegalStateException e) {
            System.out.println("键重复：" + e.getMessage());
        }

        // 用 mergeFunction 解决：同性别的姓名拼接
        Map<String, String> merged = list.stream().collect(Collectors.toMap(
                s -> s.split("-")[1],
                s -> s.split("-")[0],
                (a, b) -> a + "," + b
        ));
        System.out.println(merged);
    }
}
