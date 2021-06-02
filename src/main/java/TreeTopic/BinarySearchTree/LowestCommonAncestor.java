package TreeTopic.BinarySearchTree;

import util.TreeNode;

/**
 * 剑指 Offer 68 - I. 二叉搜索树的最近公共祖先 Easy
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof/
 *
 * 相比单纯的二叉树，可以充分利用二叉搜索树的性质，就是是有序的，因此当前节点无法作为最近公共祖先情况
 * 一定是p、q都在当前节点的一侧子树中。
 *
 * 当然利用之前的二叉树的方式肯定也可以解决。
 */
public class LowestCommonAncestor {
    private TreeNode ancestor = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return ancestor;
    }

    private void dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return;
        }
        // p、q都在当前节点的一侧子树中，不能取等于，等于就是答案了
        if ((root.val > p.val && root.val > q.val) || (root.val < p.val && root.val < q.val)) {
            dfs(root.left, p, q);
            dfs(root.right, p, q);
        } else {
            ancestor = root;
        }
    }
}
