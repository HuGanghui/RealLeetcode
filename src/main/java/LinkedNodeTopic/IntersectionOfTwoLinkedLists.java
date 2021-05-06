package LinkedNodeTopic;

import java.util.HashMap;
import java.util.Map;

/**
 * 160. 相交链表 Easy
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 *
 * 类似查找相同元素的题，哈希表貌似都可以实现，当然题解有双指针方法来可以让空间复杂度到O(1)
 */
public class IntersectionOfTwoLinkedLists {
    private Map<ListNode, Integer> map = new HashMap<>();

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        while (headA != null) {
            map.put(headA, headA.val);
            headA = headA.next;
        }
        while (headB != null) {
            if (map.get(headB) != null) {
                return headB;
            }
        }
        return null;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) {
        this.val = val;
    }
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}