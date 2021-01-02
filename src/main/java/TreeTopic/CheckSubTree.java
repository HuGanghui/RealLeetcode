package TreeTopic;

import util.TreeNode;

/**
 * 面试题 04.10. 检查子树 Median
 * https://leetcode-cn.com/problems/check-subtree-lcci/
 *
 * 这题有用到递归中的递归，其实发现二叉树的题目，大部分都是要么DFS，要么BFS，
 * 而且递归写法很简单。
 */
public class CheckSubTree {
    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if (t2 == null) {
            return true;
        }
        if (t1 == null) {
            return false;
        }
        if (t1.val == t2.val) {
            if (isSameTree(t1, t2)) {
                return true;
            }
        }
        return checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2);
    }

    private boolean isSameTree(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }

        if (t1 == null || t2 == null) {
            return false;
        }

        if (t1.val == t2.val) {
            return isSameTree(t1.left, t2.left) && isSameTree(t2.right, t2.right);
        } else {
            return false;
        }
    }
}
