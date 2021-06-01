package TreeTopic.BinarySearchTree;

/**
 * 剑指 Offer 33. 二叉搜索树的后序遍历序列 Median
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
 *
 * 二叉搜索树的定义便是每个节点的左子树所有节点都不大于该节点，右子树的所有节点都不小于该节点，是个递归定义的，
 * 本题便是利用后序遍历的特点，序列的最后一个是当前节点的值，然后去找第一个大于该节点的值，
 * 在这之前便是该节点的左子树，之后是右子树，然后判断第一个大于的节点后的所有值都不小于该节点的值再加上左右子树
 * 也满足条件，便OK。
 */
public class VerifyPostorder {
    public boolean verifyPostorder(int[] postorder) {
        return dfs(postorder, 0, postorder.length - 1);
    }

    private boolean dfs(int[] postorder, int left, int right) {
        if (left >= right) {
            return true;
        }

        int last = postorder[right];
        int firstBig = left;
        // 等号要加，因为可能右子树为null的情况
        for (int i = left; i <= right; i++) {
            if (postorder[i] >= last) {
                firstBig = i;
                break;
            }
        }
        boolean result = true;
        for (int i = firstBig; i <= right; i++) {
            if (postorder[i] < last) {
                result = false;
            }
        }
        return result && dfs(postorder, left, firstBig-1) && dfs(postorder, firstBig, right -1);
    }
}
