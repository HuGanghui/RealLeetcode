package DoublePointerTopic.FastSlowPointer;

import util.ListNode;

/**
 * 876. 链表的中间结点 Easy
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 *
 * 快慢指针直观的运用，不过注意的就是当中间节点有两个，slow指向的是第二个，如果
 * 需要第一个，需要一些微调。
 */
public class MiddleNode {
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
