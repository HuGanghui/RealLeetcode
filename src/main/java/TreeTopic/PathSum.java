package TreeTopic;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 113. 路径总和 II Median
 * https://leetcode-cn.com/problems/path-sum-ii/
 *
 * 这题一看就是回溯方法的典型，只不过是二叉树而已，因此无脑回溯，
 * 但是发现有坑，边界条件要特别注意，如果以currSum == sum 结束，由于
 * 每个叶子节点会有两个null，因此导致结果的重复，因此需要在叶子节点就判断
 * 结束条件。还有就是本题一定要求是叶子节点才行，一开始忽略了。
 */
public class PathSum {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        int currSum = 0;
        backtrack(root, sum, result, temp, currSum);
        return result;
    }

    private void backtrack(TreeNode root, int sum,
                           List<List<Integer>> result, List<Integer> temp, int currSum) {

        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null && currSum + root.val == sum) {
            temp.add(root.val);
            result.add(new ArrayList<>(temp));
            temp.remove(temp.size()-1);
            return;
        }

        temp.add(root.val);
        backtrack(root.left, sum, result, temp, currSum + root.val);
        temp.remove(temp.size()-1);

        temp.add(root.val);
        backtrack(root.right, sum, result, temp, currSum + root.val);
        temp.remove(temp.size()-1);
    }
}
