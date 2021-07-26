package LinkedNodeTopic;

/**
 * 143. 重排链表 Median
 * https://leetcode-cn.com/problems/reorder-list/
 *
 * 思路就是先找到中间节点，然后将后半段反转，然后合并两个链表。
 * 因此，算是涉及了三个简单题，构成一道综合题。
 */
public class ReorderList {
    public void reorderList(ListNode head) {
        // 先找到中间节点，然后将后半段反转，然后合并两个链表
        ListNode mid = findMidNode(head);
        ListNode head1 = head;
        ListNode tmp = mid.next;
        mid.next = null;
        ListNode head2;
        if (tmp != null) {
            head2 = reverseNode(tmp);
        } else {
            head2 = tmp;
        }
        mergeTwoLink(head1, head2);
    }

    private ListNode findMidNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverseNode(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode last = reverseNode(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    private ListNode mergeTwoLink(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode();
        ListNode last = dummy;
        while (head1 != null && head2 != null) {
            dummy.next = head1;
            dummy = dummy.next;
            head1 = head1.next;

            dummy.next = head2;
            dummy = dummy.next;
            head2 = head2.next;
        }

        if (head1 != null) {
            dummy.next = head1;
        }

        if (head2 != null) {
            dummy.next = head2;
        }
        return last.next;
    }
}
