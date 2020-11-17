package StackTopic;

/**
 * 剑指 Offer 30. 包含min函数的栈 Easy
 * https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof/
 *
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 *
 * 关键在于使用一个辅助栈来完成最小元素的存储。这里我自己写了一个栈，然后使用了一个Java自带的栈，也可以
 * 两个都用Java自带的。还有一种方法是使用Stack的链表实现方法，其中的Node多一个min值
 */
public class MinStack2 {
    private Node top;

    public MinStack2() {
        top = null;
    }

    public void push(int x) {
        int min;
        if (top != null) {
            min = x < top.getMin() ? x : top.getMin();
        } else {
            min = x;
        }
        Node node = new Node(x, min, top);
        top = node;
    }

    public void pop() {
        Node node = top.getNext();
        top = node;
    }

    public int top() {
        if (top != null) {
            return top.getValue();
        } else {
            return -1;
        }
    }
    public int min() {
        return top.getMin();
    }

}

class Node {

    private int value;
    private int min;
    private Node next;

    public Node(int value, int min, Node node) {
        this.value = value;
        this.min = min;
        this.next = node;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
