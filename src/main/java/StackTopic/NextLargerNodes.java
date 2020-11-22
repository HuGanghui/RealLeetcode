package StackTopic;

import java.util.Stack;

/**
 * 1019. 链表中的下一个更大节点 Medium
 * https://leetcode-cn.com/problems/next-greater-node-in-linked-list/
 *
 * 这题概念上仍然需要过去的元素和未来的元素进行比较，因此使用栈是合适的。
 * 不过题解中还有使用DP的，回头可以思考一下。
 */
public class NextLargerNodes {
    public int[] nextLargerNodes(ListNode head) {
        Stack<Pairs> stack = new Stack<>();
        int[] temp = new int[10000 + 10];
        int index = 0;
        while (head != null) {
            while (!stack.isEmpty() && stack.peek().value < head.val) {
                temp[stack.pop().index] = head.val;
            }
            Pairs pairs = new Pairs(index, head.val);
            stack.push(pairs);
            temp[index] = 0;
            head = head.next;
            index++;
        }
        int[] result = new int[index];
        for (int i = 0; i < index; i++) {
            result[i] = temp[i];
        }
        return result;
    }
}

class Pairs {
    int index;
    int value;

    public Pairs(int index, int value) {
        this.index = index;
        this.value = value;
    }
}

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
}

