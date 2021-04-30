package DoublePointerTopic;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 653. 两数之和 IV - 输入 BST Easy
 * https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst/
 *
 * 这题的核心还是TwoSum，只不过从数组换成了BST，但是平衡二叉树的性质就是
 * 中序遍历是有序数组，显然就用双指针就行了。
 */
public class TwoSumIV {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            int sum = list.get(left) + list.get(right);
            if (sum == k) {
                return true;
            } else if (sum < k){
                left++;
            } else {
                right--;
            }
        }
        return false;
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }
}
