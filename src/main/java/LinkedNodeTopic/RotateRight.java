package LinkedNodeTopic;

/**
 * 61. 旋转链表 Median
 * https://leetcode-cn.com/problems/rotate-list/
 *
 * 这题本质上就是找到断链位置，然后做一次断链，以及一次原始
 * 尾节点的连接就行，找尾结点需要一次遍历，找断链位置最坏也需要
 * 一次。然后注意特殊情况处理一下就行。
 */
public class RotateRight {
    public ListNode rotateRight(ListNode head, int k) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode temp = dummy;
        int n = 0;
        // 获取最后一个节点，最后需要连接
        ListNode last = dummy;
        while (temp.next != null) {
            n++;
            temp = temp.next;
            if (temp.next == null) {
                last = temp;
            }
        }
        if (n == 0) {
            return head;
        }
        // 求余操作
        int change = n - k % n;
        if (change == n) {
            return head;
        }
        // 获取断链位置的节点
        temp = dummy;
        for (int i = 0; i < change; i++) {
            temp = temp.next;
        }
        ListNode newHead = temp.next;
        temp.next = null;
        last.next = dummy.next;
        return newHead;
    }
}
