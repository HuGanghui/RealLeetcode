package DesignTopic;

/**
 * 622. 设计循环队列 Median
 * https://leetcode-cn.com/problems/design-circular-queue/
 *
 * 使用数组双指针的方式完成循环队列，当然也可以使用单链表-头尾节点的形式，
 * 但相对需要处理的边界条件多一些：当size=0时的入队和出队操作。
 */
public class MyCircularQueue {
    private int f = 0;
    private int r = 0;
    private int[] array;
    private final int CAP;

    public MyCircularQueue(int k) {
        this.CAP = k+1;
        array = new int[this.CAP];
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        array[r] = value;
        r = (r + 1) % CAP;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        f = (f + 1) % CAP;
        return true;
    }

    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return array[f];
    }

    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return array[(r + CAP-1) % CAP];
    }

    public boolean isEmpty() {
        return f == r;
    }

    public boolean isFull() {
        return ((r - f + this.CAP) % CAP) == (CAP - 1);
    }
}
