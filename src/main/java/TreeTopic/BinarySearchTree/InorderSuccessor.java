package TreeTopic.BinarySearchTree;

import util.TreeNode;

/**
 * 剑指 Offer II 053. 二叉搜索树中的中序后继 Median
 * https://leetcode-cn.com/problems/P5rCT8/
 *
 * 同样维护前一个节点，然后结合中序遍历即可。
 */
public class InorderSuccessor {
    private TreeNode prev = null;
    private TreeNode res;
    // 剪枝操作
    private boolean flag = false;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        inorder(root, p);
        return res;
    }

    private void inorder(TreeNode node, TreeNode p) {
        if (node == null) {
            return;
        }

        if (flag) {
            return;
        }

        inorder(node.left, p);

        // 具体操作
        if (prev == p) {
            res = node;
            flag = true;
        }
        prev = node;

        inorder(node.right, p);
    }
}
