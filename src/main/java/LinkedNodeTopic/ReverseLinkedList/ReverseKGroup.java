package LinkedNodeTopic.ReverseLinkedList;

import util.ListNode;

/**
 * 25. K 个一组翻转链表 Hard
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 *
 * 这题和反转链表的一个区间两道题都是复用了反转链表的前k个元素这一函数，然后
 * 都是在递归中包含了递归，只不过根据具体的情况有一定的微调。
 */
public class ReverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode b = head;
        for (int i = 0; i < k; i++) {
            if (b == null) {
                return head;
            }
            b = b.next;
        }
        ListNode newHead = recur(head, k);
        head.next = reverseKGroup(b, k);
        return newHead;
    }

    private ListNode recur(ListNode head, int k) {
        if (k == 1) {
            return head;
        }
        ListNode last = recur(head.next, k-1);
        head.next.next = head;
        head.next = null;
        return last;
    }
}
