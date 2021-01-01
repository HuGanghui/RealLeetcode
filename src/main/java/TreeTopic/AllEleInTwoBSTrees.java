package TreeTopic;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 1305. 两棵二叉搜索树中的所有元素 Median
 * https://leetcode-cn.com/problems/all-elements-in-two-binary-search-trees/
 *
 * 充分利用二叉搜索树的性质，中序遍历就可获得排序后的结果，然后使用二路归并排序即可，时间复杂度
 * O(m + n)。
 */
public class AllEleInTwoBSTrees {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        inorderTree(root1, list1);
        List<Integer> list2 = new ArrayList<>();
        inorderTree(root2, list2);
        List<Integer> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i) <= list2.get(j)) {
                result.add(list1.get(i));
                i++;
            } else {
                result.add(list2.get(j));
                j++;
            }
        }
        if (i == list1.size()) {
            for(int k = j; k < list2.size(); k++) {
                result.add(list2.get(k));
            }
        } else {
            for(int k = i; k < list1.size(); k++) {
                result.add(list1.get(k));
            }
        }
        return result;
    }

    private void inorderTree(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorderTree(root.left, list);
        list.add(root.val);
        inorderTree(root.right, list);
    }
}
