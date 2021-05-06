package LinkedNodeTopic;

/**
 * 203. 移除链表元素 Easy
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 *
 * 有可能移除头链表，因此需要一个哑节点。
 */
public class removeElementsWithVal {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode ptr = dummy;
        while (ptr.next != null) {
            if (ptr.next.val == val) {
                ptr.next = ptr.next.next;
            } else {
                ptr = ptr.next;
            }
        }
        return dummy.next;
    }
}
