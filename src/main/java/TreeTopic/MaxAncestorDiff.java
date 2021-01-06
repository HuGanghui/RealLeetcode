package TreeTopic;

import util.TreeNode;

/**
 * 1026. 节点与其祖先之间的最大差值 Median
 * https://leetcode-cn.com/problems/maximum-difference-between-node-and-ancestor/
 *
 * 这题需要比较每个从root-leaf路径上的最大差值，有两种思路，自上而下和自下而上，
 * 不管是哪种就是要保存到目前这个节点之前的路径的最大值、最小值，然后和当前节点做差比较。
 * 自上而下写法比较简洁，因为只要比较两个值，自下而上需要比较四个，写法比较繁琐。
 */
public class MaxAncestorDiff {
    private int maxDiff = 0;

    // 自上而下
    public int maxAncestorDiff(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root, root.val, root.val);
        return maxDiff;
    }

    private void dfs(TreeNode node, int max, int min) {
        if (node == null) {
            return;
        }

        int diff = Math.abs(node.val - max);
        maxDiff = maxDiff > diff ? maxDiff : diff;
        diff = Math.abs(node.val - min);
        maxDiff = maxDiff > diff ? maxDiff : diff;

        max = node.val > max ? node.val : max;
        min = node.val < min ? node.val : min;
        dfs(node.left, max, min);
        dfs(node.right, max, min);

    }
}
