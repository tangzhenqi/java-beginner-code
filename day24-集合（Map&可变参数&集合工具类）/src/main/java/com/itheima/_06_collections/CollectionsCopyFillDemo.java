package com.itheima._06_collections;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Collections.copy / fill。
 *
 *   copy(List<T> dest, List<T> src)
 *      - 把 src 的元素拷贝到 dest 的前 src.size() 个位置上（覆盖）
 *      - 注意：dest 的"长度（size）"必须 >= src 的长度，否则抛 IndexOutOfBoundsException
 *
 *   fill(List<T> list, T obj)
 *      - 把 list 中所有元素都替换为 obj
 */
public class CollectionsCopyFillDemo {
    public static void main(String[] args) {
        ArrayList<Integer> src  = new ArrayList<>();
        ArrayList<Integer> dest = new ArrayList<>();
        Collections.addAll(src,  1, 2, 3, 4, 5);
        Collections.addAll(dest, 0, 0, 0, 0, 0, 0, 0);     // 长度更大，足以承接

        Collections.copy(dest, src);
        System.out.println("src       : " + src);
        System.out.println("copy 后dest: " + dest);

        // fill：批量赋值
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 2, 3, 4, 5);
        Collections.fill(list, 100);
        System.out.println("fill 后    : " + list);
    }
}
