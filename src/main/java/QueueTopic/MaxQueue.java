package QueueTopic;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer 59 - II. 队列的最大值 Medium
 *
 * https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof/
 *
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 *
 *
 */
class MaxQueue {

    private Deque<Integer> dequeOne = new ArrayDeque<>();

    private Deque<Integer> dequeTwo = new LinkedList<>();

    public MaxQueue() {

    }

    public int max_value() {
        if (!dequeOne.isEmpty()){
            return dequeTwo.peekFirst();
        } else {
            return -1;
        }

    }

    public void push_back(int value) {
        dequeOne.addLast(value);
        while (!dequeTwo.isEmpty() && dequeTwo.peekLast() < value) {
            dequeTwo.removeLast();
        }
        dequeTwo.addLast(value);
    }

    public int pop_front() {
        int ele;
        if (!dequeOne.isEmpty()) {
            ele = dequeOne.pollFirst();
            if (ele == dequeTwo.peekFirst()) {
                dequeTwo.pollFirst();
            }
        } else {
            ele = -1;
        }
        return ele;
    }

    public static void main(String[] args) {
        MaxQueue obj = new MaxQueue();
        System.out.println(obj.max_value());
        obj.push_back(2);
        System.out.println(obj.max_value());
        obj.push_back(1);
        System.out.println(obj.max_value());
        System.out.println(obj.max_value());
    }
}

