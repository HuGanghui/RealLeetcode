package LinkedNodeTopic;

/**
 * 剑指 Offer 52. 两个链表的第一个公共节点 Easy
 * https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/
 *
 * 这题的trick在于可能存在两个链表不相交的情况，因此需要利用first（sec） == null，跳到null这个
 * 方式来处理，而first.next（sec.next） == null 则无法处理不相交的情况。
 */
public class GetIntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode first = headA;
        ListNode sec = headB;
        while (first != sec) {
            if (first == null) {
                first = headB;
            } else {
                first = first.next;
            }
            if (sec == null) {
                sec = headA;
            } else {
                sec = sec.next;
            }
        }
        return first;
    }
}
