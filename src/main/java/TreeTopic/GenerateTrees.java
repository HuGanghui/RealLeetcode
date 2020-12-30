package TreeTopic;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 95. 不同的二叉搜索树 II Median
 * https://leetcode-cn.com/problems/unique-binary-search-trees-ii/
 *
 * 直观上是递归的思路，但是我没想到可以直接返回List<TreeNode>，然后for-for来拼接，
 * 之前没遇到过，说明还是递归/DFS掌握不到位呀。
 */
public class GenerateTrees {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return dfs(1, n);
    }

    private List<TreeNode> dfs(int start, int end) {
        List<TreeNode> allTrees = new ArrayList<>();

        if (start > end) {
            allTrees.add(null); // 需要有null，否则是空的
            return allTrees;
        }

        for (int i = start; i <=end; i++) {
            List<TreeNode> leftTrees = dfs(start, i-1);
            List<TreeNode> rightTrees = dfs(i+1, end);

            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode node = new TreeNode(i);
                    node.left = left;
                    node.right = right;
                    allTrees.add(node);
                }
            }
        }

        return allTrees;
    }
}
