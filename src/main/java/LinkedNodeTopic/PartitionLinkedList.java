package LinkedNodeTopic;

/**
 * 86. 分隔链表 Median
 * https://leetcode-cn.com/problems/partition-list/
 *
 * 这题最直观的做法就是构建一个小于的链表，一个大于的链表，
 * 然后最后拼接起来，但是要注意的是对于尾部的要习惯性设置一下
 * null，否则会有循环链表。并且也用到了哑元节点的技巧。
 */
public class PartitionLinkedList {
    public ListNode partition(ListNode head, int x) {
        ListNode smaller = new ListNode();
        ListNode smallerHead = smaller;
        ListNode larger = new ListNode();
        ListNode largerHead = larger;
        while(head != null) {
            if (head.val < x) {
                smaller.next = head;
                smaller = smaller.next;
                head = head.next;
            } else {
                larger.next = head;
                larger = larger.next;
                head = head.next;
            }
        }
        // 需要设置一下，否则会有循环
        larger.next = null;
        smaller.next = largerHead.next;
        return smallerHead.next;
    }
}
