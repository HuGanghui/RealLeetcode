package DoublePointerTopic.FastSlowPointer;

import util.ListNode;

/**
 * 142. 环形链表 II Median
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/
 *
 * 这里涉及到一个计算，a + n(b + c) + b = 2(a + b)
 * a = c + (n - 1)(b + c) 因此 当fast和slow相等时说明有环，然后再
 * 令一个指针从头开始和slow依次前进，当遇到了就是环的入口。
 */
public class DetectCycle {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode ptr = head;
        boolean Cycle = false;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) {
                Cycle = true;
                while(ptr != slow) {
                    slow = slow.next;
                    ptr = ptr.next;
                }
                break;
            }
        }
        if (!Cycle) {
            return null;
        }
        return ptr;
    }
}
