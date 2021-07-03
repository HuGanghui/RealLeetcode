package DesignTopic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 706. 设计哈希映射 Easy
 * https://leetcode-cn.com/problems/design-hashmap/
 *
 * 基本思想
 * 哈希函数：整数求余 hash(x) = x mod base，base选择素数，如769
 * 解决冲突方法：链地址法
 *
 * 与哈希集合基本一致，只不过存储的元素从value变成了key-value。
 */
public class MyHashMap {
    private final int BASE = 769;
    private List<List<Pair>> buckets = new ArrayList<>(BASE);

    /** Initialize your data structure here. */
    public MyHashMap() {
        for (int i = 0; i < BASE; i++) {
            buckets.add(new LinkedList<>());
        }
    }

    private int hash(int key) {
        return key % BASE;
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int index = hash(key);
        Iterator<Pair> iterator = buckets.get(index).iterator();
        while (iterator.hasNext()) {
            Pair ele = iterator.next();
            if (ele.key == key) {
                ele.value = value;
                return;
            }
        }
        buckets.get(index).add(new Pair(key, value));
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int index = hash(key);
        Iterator<Pair> iterator = buckets.get(index).iterator();
        while (iterator.hasNext()) {
            Pair ele = iterator.next();
            if (ele.key == key) {
                return ele.value;
            }
        }
        return -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int index = hash(key);
        Iterator<Pair> iterator = buckets.get(index).iterator();
        while (iterator.hasNext()) {
            Pair ele = iterator.next();
            if (ele.key == key) {
                buckets.get(index).remove(ele);
                return;
            }
        }
    }

    class Pair {
        public int key, value;
        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
