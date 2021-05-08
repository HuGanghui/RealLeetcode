package LinkedNodeTopic.ReverseLinkedList;

import util.ListNode;

/**
 * 92. 反转链表 II Median
 * https://leetcode-cn.com/problems/reverse-linked-list-ii/
 *
 * labuladong 提供了一个递归的通解，可以反转任意m-n的节点，并且是
 * 逐步逐步的改进，从反转整个，到反转前n个，再到反转第m-n个节点。
 * 不过也提到递归解法使用了更多的栈空间，迭代解法则更高效。
 */
public class ReverseListII {
    ListNode successor = null;

    /**
     * 反转链表的前N个节点 labuladong改进题
     * https://mp.weixin.qq.com/s/5wz_YJ3lTkDH3nWfVDi5SA
     *
     * 这是labuladong在反转链表的基础上改进的一道题，从反转整个链表到
     * 反转前N个。
     */
    public ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }
        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return last;
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == 1) {
            return reverseN(head, n);
        }
        head.next = reverseBetween(head.next, m-1, n-1);
        return head;
    }

    /**
     * 利用ReverseList的迭代解法，关键构建哑元节点也是需要的，因为头节点也可能改变，这个
     * 技巧对于链表操作的相关题目真的非常重要。
     *
     * 然后要转换为之前的反转链表的话，还有一个关键的就是中间节点的最后一个要断链，否则
     * 不正确，因为之前的代码是以null为结束标志的。
     */
    public ListNode reverseBetweenIteration(ListNode head, int left, int right) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode leftNode;
        ListNode temp = dummy;
        for (int i = 0; i < left-1; i++) {
            temp = temp.next;
        }
        leftNode = temp;
        ListNode rightNode;
        ListNode partLast;
        temp = dummy;
        for (int i = 0; i < right; i++) {
            temp = temp.next;
        }
        rightNode = temp.next;
        // 断链
        temp.next = null;
        ListNode partFirst = reverse(leftNode.next);
        leftNode.next.next = rightNode;
        leftNode.next = partFirst;
        return dummy.next;
    }

    // 传统的反转整个链表的迭代
    private ListNode reverse(ListNode head) {
        ListNode first = null;
        ListNode sec = head;
        while (sec != null) {
            ListNode third = sec.next;
            sec.next = first;
            first = sec;
            sec = third;
        }
        return first;
    }
}
