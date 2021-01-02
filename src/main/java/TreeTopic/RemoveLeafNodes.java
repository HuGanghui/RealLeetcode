package TreeTopic;

import util.TreeNode;

/**
 * 1325. 删除给定值的叶子节点 Median
 * https://leetcode-cn.com/problems/delete-leaves-with-a-given-value/
 *
 * 这题我也想到了用后序遍历，但是我却没有想到如何去处理，当子节点删除后，父节点该
 * 如何接着删除，然后看题解发现可以返回左右子树，如果删除了就返回null，否则就正常返回。
 */
public class RemoveLeafNodes {
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        return dfs(root, target);
    }

    private TreeNode dfs(TreeNode node, int target) {
        if (node == null) {
            return null;
        }
        TreeNode left = dfs(node.left, target);
        TreeNode right = dfs(node.right, target);
        if (left == null && right == null && node.val == target) {
            return null;
        } else {
            node.left = left;
            node.right = right;
            return node;
        }
    }
}
