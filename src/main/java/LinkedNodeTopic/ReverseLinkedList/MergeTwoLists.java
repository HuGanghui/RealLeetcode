package LinkedNodeTopic.ReverseLinkedList;

import util.ListNode;

/**
 * 21. 合并两个有序链表 Easy
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 *
 * 类似归并排序中的合并过程，不过一开始由于无法确定使用哪个节点，因此建立
 * 一个哑元节点这个技巧非常的好用。
 */
public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode result = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                head.next = l1;
                head = head.next;
                l1 = l1.next;
            } else {
                head.next = l2;
                head = head.next;
                l2 = l2.next;
            }
        }

        if (l1 != null) {
            head.next = l1;
        }

        if (l2 != null) {
            head.next = l2;
        }

        return result.next;
    }
}
