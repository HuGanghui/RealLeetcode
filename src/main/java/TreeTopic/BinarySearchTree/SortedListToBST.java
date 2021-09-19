package TreeTopic.BinarySearchTree;

import util.*;

/**
 * 109. 有序链表转换二叉搜索树 Median
 * https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/
 *
 * 找中间节点，作为根节点，随后递归构建左右节点即可，这题找中间节点，
 * 略有不同，就是结尾不一定是null，可能是其它节点。
 */
public class SortedListToBST {
    public TreeNode sortedListToBST(ListNode head) {
        // 找中间节点，作为根节点，随后递归构建左右节点即可
        return dfs(head, null);
    }

    private TreeNode dfs(ListNode head, ListNode tail) {
        if (head == tail) {
            return null;
        }
        ListNode mid = findMin(head, tail);
        TreeNode root = new TreeNode(mid.val);
        root.left = dfs(head, mid);
        root.right = dfs(mid.next, tail);
        return root;
    }

    private ListNode findMin(ListNode head, ListNode tail) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != tail && fast.next != tail) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }
}
