package LinkedNodeTopic;

import java.util.HashSet;
import java.util.Set;

/**
 * 面试题 02.01. 移除重复节点 Easy
 * https://leetcode-cn.com/problems/remove-duplicate-node-lcci/
 *
 * 这题的区别在于无序，因此需要利用哈希表，并且需要删除因此用first.next来
 * 进行判断。
 */
public class RemoveDuplicateNodes {
    public ListNode removeDuplicateNodes(ListNode head) {
        Set<Integer> set = new HashSet<>();
        if (head == null) {
            return head;
        }
        set.add(head.val);
        ListNode first = head;
        while(first.next != null) {
            if (set.contains(first.next.val)) {
                first.next = first.next.next;
            } else {
                set.add(first.next.val);
                first = first.next;
            }
        }
        return head;
    }
}
