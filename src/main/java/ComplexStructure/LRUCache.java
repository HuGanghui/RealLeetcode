package ComplexStructure;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU 缓存机制 Median
 * https://leetcode-cn.com/problems/lru-cache/
 *
 * 关于设计复合的数据结构还是很能体现一个人的数据结构以及一定的工程抽象能力的，因此值得
 * 做一些总结。LRU (Least Recently Used)就是一个很好的例子。
 *
 * 首先分析需求：需要实现两个API，一个是get(key)，如果key存在则返回，否则返回-1；一个是put(key, value)，
 * 如果该key存在，则变更其value，不存在则插入key-value。当容量达到上限，需删除最久未使用的数据。
 * 并且要求get和put的时间复杂度都是O(1)。
 *
 * 根据要求分析该数据结构的必要条件：
 * 1. 元素必须要有一定的时序顺序，这样才能保证能够删除到最久未使用的；
 * 2. 在进行get和put时，都需要将该key-value更新为最近使用的，也就是快速
 *    插入和删除的能力；
 * 3. get要有根据key在O(1)内找到value的能力；
 *
 * 很明显，哈希表满足第3点，常数时间查找但数据无序，而链表满足第1和2两点有序且快速插入和删除，但是查找慢，
 * 因此有结合两者的数据结构：哈希链表-LinkedHashMap。这就是LRU的核心数据结构。
 *
 * 所以我们明确了需要实现的：1. 双向链表（单向不行的原因在于删除某个元素需要知道其前一个元素）2. 哈希表
 * 其中哈希表可以使用现成的（当然也可以自己实现），但是双向链表需要自己实现，因为Java中的LinkedList
 * 不提供直接面向节点的操作方法。
 *
 * 并且还有一个值得主要的，由于有两个数据结构在进行一系列的插入/删除/更新操作的时候两者都需要一起完成，
 * 因此很优秀的设计的做法是再封装一层API，不直接在get/put中完成两者的同时维护，避免遗忘。
 */
public class LRUCache {

    private Map<Integer, Node> map;
    private DoubleLinkedList cache;
    private final int CAP;

    public LRUCache(int size) {
        map = new HashMap<>();
        cache = new DoubleLinkedList();
        CAP = size;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            makeRecently(key);
            return map.get(key).value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            remove(key);
        } else if (CAP == cache.size()) {
            removeLeastRecently();
        }
        addRecently(key, value);
    }

    private void makeRecently(int key) {
        Node x = map.get(key);
        cache.remove(x);
        cache.addLast(x);
    }

    private void addRecently(int key, int value) {
        Node x = new Node(key, value);
        map.put(key, x);
        cache.addLast(x);
    }

    private void remove(int key) {
        Node x = map.remove(key);
        cache.remove(x);
    }

    private void removeLeastRecently() {
        Node first = cache.removeFirst();
        map.remove(first.key);
    }
}

class Node {
    public int key, value;
    public Node prev, next;
    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class DoubleLinkedList {
    private Node head, tail;
    private int size;

    public DoubleLinkedList() {
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public void addLast(Node x) {
        x.prev = tail.prev;
        x.next = tail;
        tail.prev.next = x;
        tail.prev = x;
        size++;
    }

    public void remove(Node x) {
        x.prev.next = x.next;
        x.next.prev = x.prev;
        size--;
    }

    public Node removeFirst() {
        Node first = head.next;
        if (first != tail) {
            remove(first);
        }
        return first;
    }

    public int size() {
        return size;
    }
}
