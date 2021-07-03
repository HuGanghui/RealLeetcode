package DesignTopic.ComplexStructure;

import java.util.HashMap;
import java.util.Map;

/**
 * 460. LFU 缓存 Hard
 * https://leetcode-cn.com/problems/lfu-cache/
 *
 * 相比LRU，其实LFU就是多了一层Freq的分层，在每个相同的Freq中，其实还是
 * LRU，然后需要同时维护的基础元素有三个：map，freq2Node和minFreq。
 * 基本的思路其实和LRU还是类似的，先列出get和put的执行框架，然后完善框架中涉及到的
 * 辅助函数，并同时完善辅助的数据结构如Node，DoubleLinkedList。
 * DoubleLinkedList 需要自行实现，而不是直接使用Java现有的LinkedList，因为其不
 * 提供直接面向节点操作的API，导致时间复杂度无法保证O(1)。
 *
 * 最关键的还是在辅助函数中，思考需要同时维护三个基础元素，要做到不重不漏。
 */
class LFUCache {
    private final int CAP;
    private Map<Integer, Node> map;
    private Map<Integer, DoubleLinkedList> freq2Node;
    private int minFreq = 0;

    public LFUCache(int capacity) {
        this.CAP = capacity;
        map = new HashMap<>();
        freq2Node = new HashMap<>();
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            updateFreq(key);
            return map.get(key).value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (this.CAP == 0) {
            return;
        }
        if (map.containsKey(key)) {
            updateFreq(key, value);
        } else if (map.size() == CAP) {
            removeMinFreqAndLRU();
            addKey(key, value);
        } else {
            addKey(key, value);
        }
    }

    private void updateFreq(int key) {
        updateFreq(key, null);
    }

    private void updateFreq(int key, Integer value) {
        Node node = map.get(key);
        freq2Node.get(node.freq).remove(node);
        if (minFreq == node.freq && freq2Node.get(node.freq).isEmpty()) {
            minFreq += 1;
        }
        node.freq += 1;
        if (value != null) {
            node.value = value;
        }
        if (!freq2Node.containsKey(node.freq)) {
            freq2Node.put(node.freq, new DoubleLinkedList());
        }
        freq2Node.get(node.freq).addLast(node);
    }

    private void removeMinFreqAndLRU() {
        Node removed = freq2Node.get(minFreq).removeFirst();
        map.remove(removed.key);
        if (freq2Node.get(minFreq).isEmpty()) {
            minFreq += 1;
        }
    }

    private void addKey(int key, int value) {
        Node node = new Node(key, value);
        node.freq = 1;
        map.put(key, node);
        if (!freq2Node.containsKey(node.freq)) {
            freq2Node.put(node.freq, new DoubleLinkedList());
        }
        freq2Node.get(node.freq).addLast(node);
        if (minFreq == 0 || minFreq > node.freq) {
            minFreq = node.freq;
        }
    }

    class Node {
        public int key, value, freq;
        public Node prev, next;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    class DoubleLinkedList {
        private Node head, tail;
        private int size;

        DoubleLinkedList() {
            head = new Node(0,0);
            tail = new Node(0,0);
            head.next = tail;
            tail.prev = head;
        }

        public void addLast(Node node) {
            node.prev = tail.prev;
            node.next = tail;
            tail.prev.next = node;
            tail.prev = node;
            size++;
        }

        public Node removeFirst() {
            Node first = head.next;
            if (head.next != tail) {
                remove(first);
            }
            return first;
        }

        public void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        public int getSize() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }
}
