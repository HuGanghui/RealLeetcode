package TreeTopic;

import util.TreeNode;

/**
 * 剑指 Offer 26. 树的子结构 Median
 * https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/
 *
 * 这题有个不同于之前的树的题，就在于关于子结构，A中每个节点都可能作为开始节点，因此
 * 首先需要遍历这个A中每个节点，同时以每个A的节点为开始节点后进行和B的逐个节点的比较。
 */
public class IsSubStructure {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(B == null) {
            return false;
        }
        return (A != null && (recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B)));
    }

    private boolean recur(TreeNode A, TreeNode B) {
        // 当B节点为null，无所谓A节点是否还有，直接就是true
        if (B == null) {
            return true;
        }
        // B节点不为null，而A节点为null，显然就不是子结构了
        if (A == null) {
            return false;
        }
        return A.val == B.val && recur(A.left, B.left) && recur(A.right, B.right);
    }
}
