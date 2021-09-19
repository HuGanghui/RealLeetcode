package TreeTopic.BinarySearchTree;

import util.TreeNode;

import java.util.*;

/**
 * 501. 二叉搜索树中的众数 Easy
 * https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/
 *
 * 使用额外空间进行存储是简单的，难点在于如何不利用额外的空间。
 * 这里考虑到二叉搜索树的性质，相等的数肯定连接在一起，因此通过
 * 维护一个全局的最大长度以及前一个值，结合中序遍历，可以完成
 * 这个操作。
 */
public class FindMode {
    private int maxLen = 0;
    private int preVal = -1;
    private int curLen = 0;
    private List<Integer> res = new ArrayList<>();

    public int[] findMode(TreeNode root) {
        inorder(root);
        int[] result = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
        }
        return result;
    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        update(root);
        inorder(root.right);
    }

    private void update(TreeNode root) {
        if (preVal == -1 || root.val != preVal) {
            curLen = 1;
            preVal = root.val;
        } else {
            curLen++;
        }
        if (curLen == maxLen) {
            res.add(root.val);
        } else if (curLen > maxLen) {
            maxLen = curLen;
            res.clear();
            res.add(root.val);
        }
    }
}
