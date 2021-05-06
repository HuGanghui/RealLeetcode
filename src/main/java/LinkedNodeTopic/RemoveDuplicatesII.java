package LinkedNodeTopic;

/**
 * 82. 删除排序链表中的重复元素 II Median
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
 *
 * 这题要求重复的都删去，因此头节点可能被删除，所以要有哑元节点，然后通过两个连续的比较来判断
 * 删除与否，通过while删除干净。
 */
public class RemoveDuplicatesII {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode first = dummy;
        while(first.next != null && first.next.next != null) {
            if (first.next.val == first.next.next.val) {
                int x = first.next.val;
                while(first.next != null && first.next.val == x) {
                    first.next = first.next.next;
                }
            } else {
                first = first.next;
            }
        }
        return dummy.next;
    }
}
