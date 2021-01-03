package DynamicProgramming;

import util.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 337. 打家劫舍 III Median
 * https://leetcode-cn.com/problems/house-robber-iii/
 *
 * 典型的DP题，但是通过不同的定义可以有不同的解法，这题就有一个直观的，用memo的
 * 每个代表了当前节点的获取的最大值；而另外一个更优的则是定义了每个节点抢/不抢的最大值，
 * 这样巧妙的避免了重复子问题，因为不需要算孙子节点的值了，从而减少了计算，比较memo还是需要
 * 去查一下的。
 */
public class HouseRobberIII {
    public int rob(TreeNode root) {
        int[] result = up2down(root);
        return Math.max(result[0], result[1]);
    }

    private int[] up2down(TreeNode node) {
        int[] result = new int[2];
        if (node == null) {
            return result;
        }
        int[] left = up2down(node.left);
        int[] right = up2down(node.right);
        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        result[1] = node.val + left[0] + right[0];
        return result;
    }
}

class HouseRobberIIIMeMo {
    public int rob(TreeNode root) {
        Map<TreeNode, Integer> memo = new HashMap<>();
        return up2down(root, memo);
    }

    private int up2down(TreeNode root, Map<TreeNode, Integer> memo) {
        if (root == null) {
            return 0;
        }
        if (memo.containsKey(root)) {
            return memo.get(root);
        }
        int robThisNode = root.val;
        if (root.left != null) {
            robThisNode = robThisNode + up2down(root.left.left, memo) + up2down(root.left.right, memo);
        }
        if (root.right != null) {
            robThisNode = robThisNode + up2down(root.right.left, memo) + up2down(root.right.right, memo);
        }
        int notRobThisNode = up2down(root.left, memo) + up2down(root.right, memo);
        int max = Math.max(robThisNode, notRobThisNode);
        memo.put(root, max);
        return max;
    }
}
