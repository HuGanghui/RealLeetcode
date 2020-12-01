package QueueTopic;

import java.util.*;

/**
 * 225. 用队列实现栈 Easy
 * https://leetcode-cn.com/problems/implement-stack-using-queues/
 *
 * 两个队列实现思路参考两个栈实现队列，还有一种一个队列的解法，
 * 每次add一个新元素，都把新元素之前的元素先出队再入队，实现更简单。
 */
public class ImplementStackUsingQueues {
    Queue<Integer> queueOne = new LinkedList();
    Queue<Integer> queueTwo = new LinkedList();


    /** Initialize your data structure here. */
    public ImplementStackUsingQueues() {

    }

    /** Push element x onto stack. */
    public void push(int x) {
        queueOne.add(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        while(queueOne.size() > 1) {
            queueTwo.add(queueOne.remove());
        }
        int result = queueOne.remove();
        while(queueTwo.size() != 0) {
            queueOne.add(queueTwo.remove());
        }
        return result;
    }

    /** Get the top element. */
    public int top() {
        while(queueOne.size() > 1) {
            queueTwo.add(queueOne.remove());
        }
        int result = queueOne.peek();
        queueTwo.add(queueOne.remove());

        while(queueTwo.size() != 0) {
            queueOne.add(queueTwo.remove());
        }
        return result;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queueOne.size() == 0;
    }
}
