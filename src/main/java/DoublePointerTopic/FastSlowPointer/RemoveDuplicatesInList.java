package DoublePointerTopic.FastSlowPointer;

import util.ListNode;

/**
 * 83. 删除排序链表中的重复元素 Easy
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 *
 * 和数组删除重复元素类似，fast探路，当不相等slow调整。
 */
public class RemoveDuplicatesInList {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null) {
            if (slow.val != fast.val) {
                slow.next = fast;
                slow = fast;
            }
            fast = fast.next;
        }
        slow.next = null;
        return head;
    }
}
