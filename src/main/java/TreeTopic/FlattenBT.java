package TreeTopic;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 114. 二叉树展开为链表 Median
 * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 *
 * 本题有歧义是原地并非指空间复杂度是O(1)，而是指尽可能在原来的节点上改变指向，
 * 然后本题最简单的做法就是前序遍历，因为需要的顺序就是前序的，然后再依次用双指针的方式改变
 * 指向，都连接到右子树就行。时间和空间复杂度都是O(n)，但这种做法遍历和连接分开了，题解还有
 * 同时进行的，不过那种需要用前序遍历的迭代方式了。后续可以再学习。
 */
public class FlattenBT {
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        preorder(root, list);
        TreeNode prev;
        TreeNode cur;
        for (int i = 0; i < list.size()-1; i++) {
            prev = list.get(i);
            cur = list.get(i+1);
            prev.left = null;
            prev.right = cur;
        }
    }

    private void preorder(TreeNode node, List<TreeNode> list) {
        if (node == null) {
            return;
        }
        list.add(node);
        preorder(node.left, list);
        preorder(node.right, list);
    }
}
