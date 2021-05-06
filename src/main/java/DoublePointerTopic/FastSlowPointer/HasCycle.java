package DoublePointerTopic.FastSlowPointer;

import util.ListNode;

/**
 * 141. 环形链表 Easy
 * https://leetcode-cn.com/problems/linked-list-cycle/
 *
 * 快慢指针的典型应用，判断链表是否有环，在O(1)的空间复杂度内完成
 * 判断。当然判断是否有重复元素都是可以用哈希表来复杂完成，不过需要额外的空间。
 */
public class HasCycle {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}
