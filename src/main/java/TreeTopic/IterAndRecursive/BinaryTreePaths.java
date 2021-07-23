package TreeTopic.IterAndRecursive;
import util.TreeNode;

import java.util.*;

/**
 * 257. 二叉树的所有路径 Easy
 * https://leetcode-cn.com/problems/binary-tree-paths/
 *
 * 使用前序遍历配合StringBuilder进行回溯，先记住加入前`int size = builder.size()`
 * 随后，使用`builder.delete(size, builder.length()+1)`进行回溯。
 *
 * 对于非递归的迭代方法，有个技巧就是，用额外的一个队列/栈来保存路径，这样就可以在变换节点的
 * 同时，得到对应的父节点的路径。
 */
public class BinaryTreePaths {

    private List<String> result = new ArrayList<>();

    public List<String> binaryTreePathsIter(TreeNode root) {
        if (root == null) {
            return result;
        } else {
            Queue<TreeNode> queue = new LinkedList<>();
            Queue<String> pathQueue = new LinkedList<>();
            queue.offer(root);
            pathQueue.offer(new StringBuilder().append(root.val).toString());
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    String path = pathQueue.poll();
                    if (node.left != null) {
                        queue.offer(node.left);
                        pathQueue.offer(new StringBuilder(path).append("->").append(node.left.val).toString());
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                        pathQueue.offer(new StringBuilder(path).append("->").append(node.right.val).toString());
                    }
                    if (node.left == null && node.right == null) {
                        result.add(path);
                    }
                }
            }
            return result;
        }
    }

    public List<String> binaryTreePathsRecursive(TreeNode root) {
        if (root == null) {
            return result;
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append(root.val);
            if (root.left == null && root.right == null) {
                result.add(builder.toString());
            }
            dfs(root.left, builder);
            dfs(root.right, builder);
        }
        return result;
    }

    private void dfs(TreeNode root, StringBuilder builder) {
        if (root == null) {
            return;
        }
        int size = builder.length();
        builder.append("->");
        builder.append(root.val);
        if (root.left == null && root.right == null) {
            result.add(builder.toString());
        }
        dfs(root.left, builder);
        dfs(root.right, builder);
        builder.delete(size, builder.length()+1);

    }
}
