package TreeTopic.BinarySearchTree;

import util.TreeNode;

/**
 * 530. 二叉搜索树的最小绝对差
 * 与众数类似，维护一个全局的最大长度以及前一个值，结合中序遍历，可以完成这个操作。
 */
public class GetMinimumDifference {
    private int min = Integer.MAX_VALUE;
    private int preVal = -1;

    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return min;
    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        getDiff(root);
        inorder(root.right);
    }

    private void getDiff(TreeNode root) {
        if (preVal == -1) {
            preVal = root.val;
        } else {
            int diff = Math.abs(root.val - preVal);
            min = Math.min(diff, min);
            preVal = root.val;
        }
    }
}
