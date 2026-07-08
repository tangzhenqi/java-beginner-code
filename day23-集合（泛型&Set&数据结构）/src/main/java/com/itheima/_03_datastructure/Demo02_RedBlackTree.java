package com.itheima._03_datastructure;

/*
 * 拓展知识点：红黑树（TreeSet / TreeMap 的底层）
 *
 *  红黑树是一种"自平衡的二叉搜索树"，每个节点带一个颜色（红 / 黑），并满足：
 *      ① 节点要么红，要么黑
 *      ② 根节点必须是黑
 *      ③ 红节点的孩子必须是黑（不能出现两个连续的红）
 *      ④ 从任意节点出发到其所有后代叶子（NIL），经过的黑色节点数量相同
 *      ⑤ 所有叶子（NIL 哨兵）都是黑色
 *
 *  这五条规则共同保证树的高度不会超过 2 * log2(n+1)，
 *  因此插入、删除、查找的最坏复杂度都是 O(log n)。
 *
 *  插入过程：
 *      1) 像普通 BST 一样找到插入位置
 *      2) 新节点先染成红色（少破坏规则 ④）
 *      3) 如果父节点也是红色 → 通过"变色 + 左旋 / 右旋"调整
 *
 *  数据结构对比：
 *      - 普通二叉搜索树：极端插入会退化成链表（O(n)）
 *      - AVL 树：严格平衡，查询更快但旋转更频繁
 *      - 红黑树：在"查询"和"插入删除"之间取得平衡，工程上最常用
 *
 *  本类用一个极简的"二叉搜索树"演示有序的核心，不实现旋转。
 *  目的：理解为什么 TreeSet.add 之后遍历会得到有序结果。
 */
public class Demo02_RedBlackTree {

    static class Node {
        int value;
        Node left, right;
        Node(int value) { this.value = value; }
    }

    private Node root;

    public void add(int value) { root = add(root, value); }

    private Node add(Node node, int value) {
        if (node == null) return new Node(value);
        if (value < node.value)      node.left  = add(node.left,  value);
        else if (value > node.value) node.right = add(node.right, value);
        // 相等就丢弃，正好模拟了 TreeSet 的去重
        return node;
    }

    /** 中序遍历：左 → 根 → 右，输出即升序。 */
    public void inorder() { inorder(root); System.out.println(); }

    private void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.value + " ");
        inorder(node.right);
    }

    public static void main(String[] args) {
        Demo02_RedBlackTree tree = new Demo02_RedBlackTree();
        int[] nums = {7, 3, 9, 1, 5, 8, 11, 5 /* 重复 */};
        for (int n : nums) tree.add(n);
        tree.inorder();   // 1 3 5 7 8 9 11
    }
}
