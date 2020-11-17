package LinkedNodeTopic;

import java.util.HashMap;

/**
 * 146. LRU缓存机制
 *
 * https://leetcode-cn.com/problems/lru-cache/
 *
 * 运用你所掌握的数据结构，设计和实现一个LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 *
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *  
 * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 * 直观想法，get，put操作就想到了Map，然后构建一个Node结构包含 key, value 和 timestamp, 作为Map的元素，但这样get是O(1)复杂度
 * 但put则需要进行遍历Map获得，复杂度为O(n)
 *
 * O(1) 解决方法是HashMap + 双向链表， 我默认 链表头为不常用部分，尾为常用部分。
 *
 */
public class LRUCache {
    private final int CAP;

    private HashMap<Integer, DLNode> hashMap;

    private DLNode header;

    private DLNode tailer;

    private int size;

    public LRUCache(int capacity) {
        CAP = capacity;
        hashMap = new HashMap<>(CAP);
        header = new DLNode();
        tailer = new DLNode();
        header.next = tailer;
        tailer.prev = header;
    }

    public int get(int key) {
        if (hashMap.containsKey(key)) {

            DLNode node = hashMap.get(key);
            DLNode prevNode = node.prev;
            prevNode.next = node.next;
            DLNode nextNode = node.next;
            nextNode.prev = prevNode;
            tailer.prev.next = node;
            node.prev = tailer.prev;
            tailer.prev = node;
            node.next = tailer;

            return node.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (hashMap.containsKey(key)) {
            DLNode node = hashMap.get(key);
            node.value = value;

            DLNode prevNode = node.prev;
            prevNode.next = node.next;
            DLNode nextNode = node.next;
            nextNode.prev = prevNode;
            tailer.prev.next = node;
            node.prev = tailer.prev;
            tailer.prev = node;
            node.next = tailer;

        } else if (size == CAP) {
            DLNode node = header.next;
            header.next = node.next;
            node.next.prev = header;
            node.next = null;
            node.prev = null;
            hashMap.remove(node.key);

            DLNode newNode = new DLNode(key, value, tailer.prev, tailer);
            tailer.prev.next = newNode;
            tailer.prev = newNode;
            hashMap.put(key, newNode);

        } else {
            DLNode node = new DLNode(key, value, tailer.prev, tailer);
            tailer.prev.next = node;
            tailer.prev = node;
            hashMap.put(key, node);
            size++;
        }
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // 返回 -1 (未找到)
        System.out.println(lRUCache.get(3));    // 返回 3
        System.out.println(lRUCache.get(4));    // 返回 4
    }
}

class DLNode {
    public DLNode prev;
    public DLNode next;
    public int key;
    public int value;

    public DLNode () {

    }

    public DLNode (int key, int value, DLNode prev, DLNode next) {
        this.key = key;
        this.value = value;
        this.prev = prev;
        this.next = next;
    }
}
