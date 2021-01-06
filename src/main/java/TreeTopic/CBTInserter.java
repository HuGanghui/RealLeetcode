package TreeTopic;

import util.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 919. 完全二叉树插入器 Median
 * https://leetcode-cn.com/problems/complete-binary-tree-inserter/
 *
 * 显然这题是希望使用完全二叉树的性质，就是可以使用顺序存储，从而通过孩子/父亲节点
 * 下标计算获得父亲/孩子节点的下标。确保insert的时间复杂度是O(1)
 */
public class CBTInserter {
    private List<TreeNode> list = new ArrayList<>(20000); // 20000 根据题目数据范围定，避免copy发生

    // 层次遍历 O(n)
    public CBTInserter(TreeNode root) {
        Deque<TreeNode> temp = new LinkedList<>();
        temp.addLast(root);
        while (!temp.isEmpty()) {
            TreeNode node = temp.removeFirst();
            list.add(node);
            if (node.left != null) {
                temp.addLast(node.left);
            }
            if (node.right != null) {
                temp.addLast(node.right);
            }
        }
    }

    // 插入 时间复杂度O(1)
    public int insert(int v) {
        TreeNode node = new TreeNode(v);
        list.add(node);
        int index = list.size() - 1;
        int parentIndex = (index - 1) / 2;
        TreeNode parentNode = list.get(parentIndex);
        if (index % 2 == 0) {
            parentNode.right = node;
        } else {
            parentNode.left = node;
        }
        return parentNode.val;
    }

    public TreeNode get_root() {
        return list.get(0);
    }
}
