package com.itheima._06_collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 拓展：Collections.unmodifiableXxx 包装为"只读视图"。
 *
 * 适用场景：把内部集合暴露给外部时，不希望对方修改。
 *
 * 注意：
 *   - 它是"视图"，原集合改了仍然会影响视图
 *   - 只读视图本身的 add/remove/set 会抛 UnsupportedOperationException
 */
public class CollectionsUnmodifiableDemo {
    public static void main(String[] args) {
        ArrayList<String> origin = new ArrayList<>();
        Collections.addAll(origin, "a", "b", "c");

        List<String> readonly = Collections.unmodifiableList(origin);

        // 通过 readonly 修改 -> 抛异常
        try {
            readonly.add("d");
        } catch (UnsupportedOperationException e) {
            System.out.println("readonly.add 抛出：" + e.getClass().getSimpleName());
        }

        // 通过原集合修改 -> 视图会跟着变（视图！不是快照）
        origin.add("d");
        System.out.println("原集合变了，readonly = " + readonly);
    }
}
