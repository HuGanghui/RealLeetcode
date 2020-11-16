package StackTopic;

import java.util.Stack;

/**
 * 剑指 Offer 30. 包含min函数的栈 Easy
 * https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof/
 *
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 *
 * 关键在于使用一个辅助栈来完成最小元素的存储。这里我自己写了一个栈，然后使用了一个Java自带的栈，也可以
 * 两个都用Java自带的。还有一种方法是使用Stack的链表实现方法，其中的Node多一个min值
 */
public class MinStack {
    public static final int CAP = 30000;

    protected int[] Stack;

    protected Stack<Integer> helpStack = new Stack<>();

    protected int top = -1;

    protected int min = Integer.MIN_VALUE;

    /** initialize your data structure here. */
    public MinStack() {
        Stack = new int[CAP];
    }

    public void push(int x) {
        if (helpStack.isEmpty() || helpStack.peek() >= x) {
            helpStack.push(x);
        }
        Stack[++top] = x;
    }

    public void pop() {
        int ele = Stack[top];
        if (ele == helpStack.peek()) {
            helpStack.pop();
        }
        top--;
    }

    public int top() {
        return Stack[top];
    }

    public int min() {
        return helpStack.peek();
    }
}