package com.itheima._03_datastructure;

/*
 * 拓展知识点：哈希表（HashSet / HashMap 的底层）
 *
 *  ┌──────────────────────────────────────────────────────────────────┐
 *  │  数组桶 0 → [k1,v1] → [k2,v2]                                    │
 *  │  数组桶 1 → null                                                  │
 *  │  数组桶 2 → [k3,v3]                                              │
 *  │  ...                                                              │
 *  │  数组桶 7 → [k4,v4] → [k5,v5] → [k6,v6] → ... → ⤵                │
 *  │                                                  红黑树（链表过长时升级）│
 *  └──────────────────────────────────────────────────────────────────┘
 *
 *  关键参数（JDK 1.8 HashMap）：
 *      DEFAULT_INITIAL_CAPACITY    = 16     默认数组长度（必须是 2 的幂）
 *      DEFAULT_LOAD_FACTOR         = 0.75   负载因子
 *      threshold = capacity * loadFactor    扩容阈值（16 * 0.75 = 12）
 *      TREEIFY_THRESHOLD           = 8      链表转树阈值
 *      MIN_TREEIFY_CAPACITY        = 64     转树需要数组长度同时 >= 64
 *      UNTREEIFY_THRESHOLD         = 6      树退化为链表的阈值
 *
 *  写入流程（put / add）：
 *      1. 计算 key 的 hashCode，再做扰动：(h ^ (h >>> 16)) & (n - 1) 得到桶下标
 *      2. 桶为空 → 新建节点放入
 *      3. 桶非空 → 沿链表逐个 equals 比较；命中则覆盖（HashMap）或丢弃（HashSet）
 *      4. 都不匹配 → 追加到链表尾，并视情况升级为红黑树
 *      5. 元素数量 > threshold → 数组扩容为 2 倍并 rehash
 *
 *  为什么数组长度要是 2 的幂？
 *      因为下标计算 (n - 1) & hash 在 n 为 2 的幂时等价于取模，但比 % 快得多。
 *
 *  本类用一个极简的"数组 + 链表"实现，帮助理解上述流程。
 */
public class Demo01_HashTable {

    /** 简化版的哈希表节点。 */
    static class Node {
        final Object key;
        Object value;
        Node next;
        Node(Object key, Object value) { this.key = key; this.value = value; }
    }

    private Node[] table = new Node[16];      // 数组桶
    private int size;

    /** 写入：与 HashMap.put 类似，但省略了红黑树升级和扩容。 */
    public void put(Object key, Object value) {
        int index = indexFor(key);
        Node head = table[index];

        // 桶为空，直接放入
        if (head == null) {
            table[index] = new Node(key, value);
            size++;
            return;
        }

        // 桶非空：先看链表上有没有 equals 命中的节点
        Node node = head;
        while (true) {
            if (node.key == null ? key == null : node.key.equals(key)) {
                node.value = value;       // 命中 → 覆盖
                return;
            }
            if (node.next == null) break;
            node = node.next;
        }

        // 未命中 → 链表尾部追加
        node.next = new Node(key, value);
        size++;
    }

    /** 读取。 */
    public Object get(Object key) {
        int index = indexFor(key);
        for (Node n = table[index]; n != null; n = n.next) {
            if (n.key == null ? key == null : n.key.equals(key)) {
                return n.value;
            }
        }
        return null;
    }

    public int size() { return size; }

    /** 与 HashMap 一致的桶下标计算：扰动 + 与上 (n-1)。 */
    private int indexFor(Object key) {
        int h = (key == null) ? 0 : key.hashCode();
        h = h ^ (h >>> 16);                // 扰动：让高 16 位也参与计算，降低碰撞
        return (table.length - 1) & h;
    }

    /** 打印每个桶里的链表，直观感受哈希表结构。 */
    public void dump() {
        for (int i = 0; i < table.length; i++) {
            System.out.print("bucket[" + i + "] -> ");
            for (Node n = table[i]; n != null; n = n.next) {
                System.out.print("(" + n.key + "=" + n.value + ") ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Demo01_HashTable map = new Demo01_HashTable();
        map.put("apple",  1);
        map.put("banana", 2);
        map.put("cherry", 3);
        map.put("Aa",     "故意制造碰撞");
        map.put("BB",     "我和上一行 hashCode 相同");
        map.put("apple",  100);            // 覆盖

        System.out.println("size = " + map.size());
        System.out.println("get(apple) = " + map.get("apple"));
        System.out.println("get(BB)    = " + map.get("BB"));
        System.out.println();
        map.dump();
    }
}
