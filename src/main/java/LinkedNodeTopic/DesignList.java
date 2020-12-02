package LinkedNodeTopic;

/**
 * 707. 设计链表 median
 * https://leetcode-cn.com/problems/design-linked-list/
 *
 * 这题特别容易出错，尤其是边界条件，最容易出错的方案：使用单指针，且没有哑头节点。
 * 使用单指针+哑头节点更不容易出错，但是仍然需要注意记得size的更改以及循环更改正确的node。
 */
public class DesignList {
    private int size;
    // 哑节点
    private Node header;

    /** Initialize your data structure here. */
    public DesignList() {
        this.size = 0;
        this.header = new Node(0, null);
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index > size -1) {
            return -1;
        }
        Node node = header;
        for (int i = 0; i < (index + 1); i++) {
            node = node.next;
        }
        return node.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) {
            return;
        }
        Node prevNode = header;
        for (int i = 0; i < index; i++) {
            prevNode = prevNode.next;
        }
        Node newNode = new Node(val, prevNode.next);
        prevNode.next = newNode;
        size++;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index > size - 1) {
            return;
        }
        Node prevNode = header;
        for (int i = 0; i < index; i++) {
            prevNode = prevNode.next;
        }
        prevNode.next = prevNode.next.next;
        size--;
    }

    private class Node {
        public int val;
        public Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        DesignList designList = new DesignList();
        designList.addAtHead(1);
        designList.addAtTail(3);
        designList.addAtIndex(1,2);
        System.out.println(designList.get(1));
        designList.deleteAtIndex(0);
        designList.get(0);
    }
}
