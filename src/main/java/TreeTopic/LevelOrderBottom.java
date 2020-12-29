package TreeTopic;

import util.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 107. 二叉树的层序遍历 II Median
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
 *
 * 层次遍历-核心利用队列，这题有个需要注意的就是每个层次的节点数量如何获取的，
 * 以及空list不需要并且要反向打印。
 */
public class LevelOrderBottom {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> list = new LinkedList<>();
        List<Integer> temp = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        while(!deque.isEmpty()) {
            int size = deque.size(); // 获取当前层的node数量
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.removeFirst();
                temp.add(node.val);
                if (node.left != null) {
                    deque.addLast(node.left);
                }
                if (node.right != null) {
                    deque.addLast(node.right);
                }
            }
            if (!temp.isEmpty()) {
                list.add(0, new ArrayList<>(temp));
                temp.clear();
            }
        }
        return list;
    }
}
