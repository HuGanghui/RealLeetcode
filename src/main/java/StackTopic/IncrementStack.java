package StackTopic;

/**
 * 1381. 设计一个支持增量操作的栈
 * https://leetcode-cn.com/problems/design-a-stack-with-increment-operation/
 *
 * 核心了解栈的构建，然后合理改造添加功能即可，这题和最小栈以及用栈模拟队列都是类似的意思。
 */
public class IncrementStack {

    private final int maxSize;

    private int[] stack;

    private int top;

    public IncrementStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
        top = -1;
    }

    public void push(int x) {
        if (!(top == maxSize - 1)) {
            stack[++top] = x;
        }
    }

    public int pop() {
        if (top >= 0) {
            return stack[top--];
        } else {
            return -1;
        }
    }

    public void increment(int k, int val) {
        if (k <= top + 1) {
            for (int i = 0; i < k; i++) {
                stack[i] += val;
            }
        } else {
            for (int i = 0; i < top + 1; i++) {
                stack[i] += val;
            }
        }
    }
}
