package TreeTopic;

import util.TreeNode;

/**
 * 剑指 Offer 68 - II. 二叉树的最近公共祖先 Easy
 * https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/
 */
public class LowestCommonAncestor {

    private TreeNode ancestor = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return ancestor;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        boolean left = dfs(root.left, p, q);
        boolean right = dfs(root.right, p, q);
        if (left && right || ((root.val == p.val || root.val == q.val) && (left || right))) {
            ancestor = root;
        }
        return left || right || root.val == p.val || root.val == q.val;
    }
}
