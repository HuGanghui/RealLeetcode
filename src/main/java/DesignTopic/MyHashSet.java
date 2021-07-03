package DesignTopic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 705. 设计哈希集合 Easy
 * https://leetcode-cn.com/problems/design-hashset/
 *
 * 基本思想
 * 哈希函数：整数求余 hash(x) = x mod base，base选择素数，如769
 * 解决冲突方法：链地址法
 *
 * 值得注意的是，对于List进行迭代的过程中，进行更改，其实不是一个很好的选择，
 * 但非得这么做，就需要更改后立刻停止迭代。
 *
 */
public class MyHashSet {
    private final int BASE = 769;
    private List<List<Integer>> buckets = new ArrayList<>(BASE);

    /** Initialize your data structure here. */
    public MyHashSet() {
        for (int i = 0; i < BASE; i++) {
            buckets.add(new LinkedList<>());
        }
    }

    private int hash(int key) {
        return key % BASE;
    }

    public void add(int key) {
        int index = hash(key);
        Iterator<Integer> iterator = buckets.get(index).iterator();
        while (iterator.hasNext()) {
            int ele = iterator.next();
            if (ele == key) {
                return;
            }
        }
        buckets.get(index).add(key);
    }

    public void remove(int key) {
        int index = hash(key);
        Iterator<Integer> iterator = buckets.get(index).iterator();
        while (iterator.hasNext()) {
            int ele = iterator.next();
            if (ele == key) {
                // 保证移除的Object而非index
                buckets.get(index).remove((Integer)key);
                return;
            }
        }
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int index = hash(key);
        Iterator<Integer> iterator = buckets.get(index).iterator();
        boolean result = false;
        while (iterator.hasNext()) {
            int ele = iterator.next();
            if (ele == key) {
                result = true;
                break;
            }
        }
        return result;
    }
}
