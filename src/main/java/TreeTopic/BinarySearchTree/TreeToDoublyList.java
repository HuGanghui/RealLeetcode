package TreeTopic.BinarySearchTree;

/**
 * 剑指 Offer 36. 二叉搜索树与双向链表 Median
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/
 *
 * 这题首先要变成有序的循环双向链表，三个点：有序、循环、双向，二叉搜索树
 * 显然中序遍历就是有序的，然后需要维护链表的全局的头节点和尾节点，构建链表
 * 常用技巧之一就是哑元节点，然后在迭代的过程中连接链表并不断更新头尾节点就行，最后
 * 再将头尾节点连接起来构成循环链表。
 */
public class TreeToDoublyList {
    private Node pre;
    private Node dummy = new Node();

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        pre = dummy;
        dfs(root);
        dummy.right.left = pre;
        pre.right = dummy.right;
        return dummy.right;
    }

    private void dfs(Node cur) {
        if (cur == null) {
            return;
        }
        dfs(cur.left);
        pre.right = cur;
        cur.left = pre;
        pre = cur;
        dfs(cur.right);
    }

    private class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
}
