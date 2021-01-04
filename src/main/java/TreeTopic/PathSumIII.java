package TreeTopic;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 437. 路径总和 III Median
 * https://leetcode-cn.com/problems/path-sum-iii/
 *
 * 本题和II的区别在于，不一定要root-leaf，中间过程中任何一段满足
 * 条件都是可以的，因此就需要每一段都考虑，基本上有这个特点就是要用
 * 前缀和，不过考虑也是要有一定顺序的，因此可以用DFS的方法，结合前缀和，
 * 每次考虑以当前节点为结尾的每一段是否满足要求。DFS时间复杂度O(n)，每个
 * 节点检查是否符合时间复杂度O(n),因此总的复杂度O(n^2)。看题解有使用map
 * 进行优化的操作，回头看看。
 *
 */
public class PathSumIII {
    public int pathSum(TreeNode root, int sum) {
        List<Integer> preSum = new ArrayList<>();
        return backtracking(root, sum, preSum);
    }

    private int backtracking(TreeNode node, int sum, List<Integer> preSum) {
        int result = 0;

        if (node == null) {
            return result;
        }

        if (preSum.size() == 0) {
            preSum.add(node.val);
        } else {
            preSum.add(preSum.get(preSum.size()-1) + node.val);
        }

        for (int i = -1; i < preSum.size()-1; i++) {
            if (isEqual(i, preSum.size()-1, preSum, sum)) {
                result++;
            }
        }

        int left = backtracking(node.left, sum, preSum);
        int right = backtracking(node.right, sum, preSum);

        preSum.remove(preSum.size()-1);
        return result + left + right;
    }

    private boolean isEqual(int start, int end, List<Integer> preSum, int sum) {
        if (start == -1) {
            return sum == preSum.get(end);
        } else {
            return sum == (preSum.get(end) - preSum.get(start));
        }
    }
}
