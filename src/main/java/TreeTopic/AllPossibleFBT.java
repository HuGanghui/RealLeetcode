package TreeTopic;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 894. 所有可能的满二叉树 Median
 * https://leetcode-cn.com/problems/all-possible-full-binary-trees/
 *
 * 很明显这题也是一个可以使用DFS的，因为所谓的满二叉树的定义就是递归的，这题也和
 * 95. 不同的二叉搜索树 II 基本上是一样的，只是细节上的区别。
 */
public class AllPossibleFBT {
    public List<TreeNode> allPossibleFBT(int N) {
        return getFullTree(N);
    }

    private List<TreeNode> getFullTree(int n) {
        List<TreeNode> allTrees = new ArrayList<>();

        if (n % 2 == 0) {
            return allTrees;
        }

        if (n == 1) {
            allTrees.add(new TreeNode(0));
            return allTrees;
        }

        for (int i = 1; i < n-1; i = i+2) {
            List<TreeNode> leftTrees = getFullTree(i);
            List<TreeNode> rightTrees = getFullTree(n - 1 - i); // 注意是 n - 1

            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode node = new TreeNode(0);
                    node.left = left;
                    node.right = right;
                    allTrees.add(node);
                }
            }
        }
        return allTrees;
    }

    public static void main(String[] args) {
        AllPossibleFBT allPossibleFBT = new AllPossibleFBT();
        allPossibleFBT.getFullTree(7);
    }
}
