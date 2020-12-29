package TreeTopic;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 144. 二叉树的前序遍历 Median
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 *
 * 前序-中左右，中序-左中右，后序-左右中。前中后的递归写法都是非常简单的，且比较统一，
 * 迭代写法则稍微有些不一样，前后还比较类似，但中序则不太一样。
 */
public class PreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        return list;
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        dfs(node.left, list);
        dfs(node.right, list);
    }
}

// 迭代写法-利用栈，但是要注意的是前序先right后left，也就倒过来，这样stack访问才是正的
class PreorderTraversalIteration {
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return list;
    }
}


