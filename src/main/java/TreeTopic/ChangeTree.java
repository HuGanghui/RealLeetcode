package TreeTopic;

import java.util.*;

/**
 * 交换子树 腾讯 2021.09.26
 * 给定一个树，以及一个二维数组，每个数组是包含2个数字，需要将数字代表的子树进行交换，
 * 如果2个数字代表的子树是祖先关系就无需交换。
 *
 * 本质是最近公共祖先 + 层次遍历的综合题，先通过公共祖先确定没有祖先关系，然后进行层次遍历，
 * 遍历过程获取2个数字代表的子树及其父亲节点，然后进行交换即可。
 *
 * 下面解法通过所有测试
 */
public class ChangeTree {

    public static void main(String[] args) {
        // {1,2,3,4,5,6,7},[[1,2],[2,3],[1,7]]
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                new TreeNode(3, new TreeNode(6), new TreeNode(7)));
        ChangeTree test = new ChangeTree();

        test.solve(root, new int[][]{{1,2}, {2, 3}, {1, 7}});
    }

    public TreeNode solve (TreeNode root, int[][] b) {
        // write code here
        int n = b.length;
        for (int i = 0; i < n; i++) {
            change(root, b[i]);
        }
        return root;
    }

    private void change(TreeNode root, int[] b) {
        if (b[0] == root.val || b[1] == root.val) {
            return;
        }
        findAncestor(root, b[0], b[1]);
        if (ancestor == b[0] || ancestor == b[1]) {
            return;
        }
        List<TreeNode> res = bfs(root, b[0], b[1]);
        TreeNode f1 = res.get(0);
        TreeNode c1 = res.get(1);
        TreeNode f2 = res.get(2);
        TreeNode c2 = res.get(3);
        if (f1.val == f2.val) {
            TreeNode tmp = f1.left;
            f1.left = f1.right;
            f1.right = tmp;
        } else {
            if (f1.left != null && f1.left.val == c1.val) {
                f1.left = c2;
            } else {
                f1.right = c2;
            }
            if (f2.left != null && f2.left.val == c2.val) {
                f2.left = c1;
            } else {
                f2.right = c1;
            }
        }
    }

    private int ancestor = -1;

    private boolean findAncestor(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }
        boolean left = findAncestor(root.left, x, y);
        boolean right = findAncestor(root.right, x, y);
        if (left && right || ((root.val == x || root.val == y) && (left || right))) {
            ancestor = root.val;
        }
        return left || right || root.val == x || root.val == y;
    }

    private List<TreeNode> bfs(TreeNode root, int x, int y) {
        Deque<TreeNode> queue = new LinkedList<>();
        List<TreeNode> res = new ArrayList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.removeFirst();
                if (node.left != null && (node.left.val == x || node.left.val == y)) {
                    res.add(node);
                    res.add(node.left);
                }
                if (node.right != null && (node.right.val == x || node.right.val == y)) {
                    res.add(node);
                    res.add(node.right);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return res;
    }

    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        public TreeNode(int val) {
            this.val = val;
        }
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
