package LinkedNodeTopic;

/**
 * 148. 排序链表 Median
 * https://leetcode-cn.com/problems/sort-list/
 *
 * 借助归并排序的思想进行处理，可以分为三个部分：
 * 1. 找中点 2.递归 3. 合并两个有序链表
 *
 * 然后记得递归需要处理结束条件，这里有个小问题，就是
 * 找中间节点的时候，可能出现一个是两个，一个是null的情况，
 * 因此结束条件需要处理一下只有两个节点的边界情况
 */
public class SortList {
    // 归并排序的思路
    // 1. 找中点 2.递归 3. 合并两个有序链表
    public ListNode sortList(ListNode head) {

        // 结束条件1
        if (head == null || head.next == null) {
            return head;
        }
        // 结束条件2
        // 处理两个节点的边界情况
        if (head.next.next == null) {
            if (head.val > head.next.val) {
                int temp = head.val;
                head.val = head.next.val;
                head.next.val = temp;
            }
            return head;
        }

        ListNode mid = findMid(head);
        ListNode sec = mid.next;
        // 中间断链
        mid.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(sec);
        ListNode newHead = merge(left, right);
        return newHead;
    }

    private ListNode findMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                tail.next = head1;
                head1 = head1.next;
            } else {
                tail.next = head2;
                head2 = head2.next;
            }
            tail = tail.next;
        }

        if (head1 != null) {
            tail.next = head1;
        }

        if (head2 != null) {
            tail.next = head2;
        }

        return dummy.next;
    }
}
