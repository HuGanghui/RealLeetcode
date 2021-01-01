package TreeTopic;

import util.TreeNode;

/**
 * 222. 完全二叉树的节点个数 Median
 * https://leetcode-cn.com/problems/count-complete-tree-nodes/
 *
 * 最简单的做法是DFS/BFS递归/遍历求和就行，时间复杂度O(n)，但是针对完全二叉树
 * 的特征，其实是可以做优化的，力扣官方题解有一种二分查找+位运算的方式，时间复杂度
 * 只要O((log(n))^2)，但是比较难想到使用位运算来模拟判断完全二叉树的最底层的第k
 * 个节点是否存在。
 */
public class CountNodes {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        TreeNode node = root;
        int depth = 0;
        while (node.left != null) {
            node = node.left;
            depth++;
        }

        if (depth == 0) {
            return 1;
        }

        int left = 1 << depth;
        int right = (1 << (depth+1)) - 1;
        int result = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (exits(root, depth, mid)) {
                // 位比较不能超过，所以多个条件
                if (mid+1 >= (1 << (depth+1)) || !exits(root, depth, mid+1)) {
                    result = mid; // 以mid为结果
                    break;
                }
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    // 路径模块是否存在
    private boolean exits(TreeNode root, int depth, int k) {
        int bits = 1 << (depth -1); // k最高位代表了当前所在层数，要跳过，
                                    // 因此使用 depth -1
        TreeNode node = root;
        while (node != null && bits > 0) { // 一种是node不存在，还一种是到底了结束
            if ((bits & k) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            bits >>= 1;
        }
        return node != null;
    }

    public static void main(String[] args) {
        CountNodes countNodes = new CountNodes();
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2, new TreeNode(4), new TreeNode(5));
        node.right = new TreeNode(3);
        countNodes.countNodes(node);
    }

}


class CountNodesDFS {
    // 很简单的DFS，前序中序后序都行，BFS层次也是可以的
    public int countNodes(TreeNode root) {
        return getNodesNum(root);
    }

    private int getNodesNum(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = getNodesNum(node.left);
        int right = getNodesNum(node.right);
        return left + right + 1;
    }
}
