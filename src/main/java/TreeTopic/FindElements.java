package TreeTopic;

import util.TreeNode;

/**
 * 1261. 在受污染的二叉树中查找元素 Median
 * https://leetcode-cn.com/problems/find-elements-in-a-contaminated-binary-tree/
 *
 * 恢复的方法很简单，就是后序递归遍历，但是find方法有新意，最简单的方法就是用set来保存每个节点元素，
 * 空间复杂度O(n)，时间复杂度O(1)。但还有一个让空间复杂度为O(1)的方法，且对于这题保存int，
 * 时间复杂度基本上也是O(log(n))，最大为32次，做法就是之前在完全二叉树求最后节点/节点个数
 * （222. 完全二叉树的节点个数）那里提到的位运算的方法，为什么这题可以用，
 * 因为元素是 2 * x + 1, 2 * x + 2，因此每个元素加上1，可以和完全二叉树：
 *                 1
 *             2       3
 *          4    5   6   7
 *        8  9 10 11 ...（
 * 对应上，然后就可以使用位运算来判断对应位置的节点是否存在。
 *
 * 这个我觉得算是一个关于完全二叉树的技巧
 */
public class FindElements {

    private TreeNode root;

    public FindElements(TreeNode root) {
        recoverTree(root, 0);
        this.root = root;
    }

    private void recoverTree(TreeNode node, int val) {
        if (node == null) {
            return;
        }
        node.val = val;

        if (node.left != null) {
            recoverTree(node.left, 2 * val + 1);
        }
        if (node.right != null) {
            recoverTree(node.right, 2 * val + 2);
        }
    }

    public boolean find(int target) {
        target++;
        int bit = Integer.highestOneBit(target) >> 1; // 找到次高位开始计算, Integer.highestOneBit就是
        // 除最高位其他位设为0
        TreeNode node = root;
        while(node != null && bit > 0) {
            if ((bit & target) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            bit >>= 1;
        }
        return node != null;
    }
}
