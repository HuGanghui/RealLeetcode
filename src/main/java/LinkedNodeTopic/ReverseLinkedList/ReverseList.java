package LinkedNodeTopic.ReverseLinkedList;

import util.ListNode;

/**
 * 206. 反转链表 Easy
 * https://leetcode-cn.com/problems/reverse-linked-list/
 *
 * 本质上就是利用双指针来进行处理，再加一个临时指针记录sec.next,
 * 有迭代的递归两种写法，不过其实思路是类似的。
 */
public class ReverseList {
    public ListNode reverseList(ListNode head) {
        ListNode first = null;
        ListNode sec = head;
        while(sec != null) {
            ListNode third = sec.next;
            sec.next = first;
            first = sec;
            sec = third;
        }
        return first;
    }

    // 递归的第一种写法
    public ListNode reverseListRecursive(ListNode first, ListNode sec) {
        if (sec == null) {
            return first;
        }
        ListNode third = sec.next;
        sec.next = first;
        return reverseListRecursive(sec, third);
    }

    // 递归的第二种写法
    public ListNode reverseListRecursiveII(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode last = reverseListRecursiveII(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }
}
