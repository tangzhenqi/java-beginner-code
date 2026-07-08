package com.itheima._04_arraylist;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * 知识点十三（拓展）：ArrayList 底层结构 & 扩容机制。
 *
 *  - 底层是 Object[] elementData
 *  - 默认初始容量 10（懒加载：无参构造时是空数组，第一次 add 才扩到 10）
 *  - 每次扩容到 原容量的 1.5 倍：newCapacity = oldCapacity + (oldCapacity >> 1)
 *  - 扩容用 Arrays.copyOf 完成（数组拷贝），所以频繁 add 大量数据时
 *    建议在构造时指定初始容量：new ArrayList<>(expectedSize)。
 *
 *  - 随机访问 O(1)（数组下标）
 *  - 任意位置 add/remove 平均 O(n)（要移动元素）
 *
 *  本类用反射打印底层数组真实长度（capacity），用 size() 看逻辑长度。
 */
public class ArrayListInternalsDemo {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        System.out.printf("初始: size=%d, capacity=%d%n", list.size(), capacityOf(list));

        for (int i = 1; i <= 20; i++) {
            list.add(i);
            if (i <= 12 || i == 15 || i == 20) {
                System.out.printf("add #%d 后: size=%d, capacity=%d%n",
                        i, list.size(), capacityOf(list));
            }
        }

        // 预分配避免扩容
        ArrayList<Integer> preallocated = new ArrayList<>(100);
        System.out.printf("预分配 100 之后: size=%d, capacity=%d%n",
                preallocated.size(), capacityOf(preallocated));
    }

    /** 反射读取 ArrayList 内部数组容量。 */
    private static int capacityOf(ArrayList<?> list) throws Exception {
        Field f = ArrayList.class.getDeclaredField("elementData");
        f.setAccessible(true);
        Object[] arr = (Object[]) f.get(list);
        return arr.length;
    }
}
