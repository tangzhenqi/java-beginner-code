package com.itheima._02_search;

/**
 * 分块查找用 JavaBean
 *   max        当前块中的最大值（要求块间有序 → 每块的 max 是单调递增的）
 *   startIndex 这一块在原数组中的起始索引（含）
 *   endIndex   这一块在原数组中的结束索引（含）
 */
public class Block {
    private int max;
    private int startIndex;
    private int endIndex;

    public Block() {}

    public Block(int max, int startIndex, int endIndex) {
        this.max = max;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public int getMax() { return max; }
    public void setMax(int max) { this.max = max; }

    public int getStartIndex() { return startIndex; }
    public void setStartIndex(int startIndex) { this.startIndex = startIndex; }

    public int getEndIndex() { return endIndex; }
    public void setEndIndex(int endIndex) { this.endIndex = endIndex; }

    @Override
    public String toString() {
        return "Block{max=" + max + ", startIndex=" + startIndex + ", endIndex=" + endIndex + "}";
    }
}
