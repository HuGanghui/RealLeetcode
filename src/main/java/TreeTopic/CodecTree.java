package TreeTopic;

import util.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer 37. 序列化二叉树 Hard
 * https://leetcode-cn.com/problems/xu-lie-hua-er-cha-shu-lcof/
 *
 * 二叉树的遍历方式有多种，除了中序遍历无法反序列化，其它都OK，其中前序遍历
 * 相对最简单，因此这里就是前序遍历的实现：
 * 序列化利用StringBuilder来存储，最后输出一个字符串即可。
 * 反序列化利用一个双向列表/队列来同样完成前序遍历即可。
 *
 * 一般情况下单个前序或其它遍历是无法复原二叉树的，但是如果添加了null节点，
 * 就可以了。
 */
public class CodecTree {
    private final String SEP = ",";
    private final String NULL = "#";
    private Deque<String> list;
    private StringBuilder builder;

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        builder = new StringBuilder();
        serializeHelp(root);
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    private void serializeHelp(TreeNode root) {
        if (root == null) {
            builder.append(NULL).append(SEP);
            return;
        }
        builder.append(root.val).append(SEP);
        serializeHelp(root.left);
        serializeHelp(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        list = new LinkedList<>();
        for (String s : data.split(SEP)) {
            list.addLast(s);
        }
        return deserializeHelp(list);
    }

    private TreeNode deserializeHelp(Deque<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }
        String first = nodes.removeFirst();
        if (first.equals(NULL)) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(first));
        node.left = deserializeHelp(nodes);
        node.right = deserializeHelp(nodes);
        return node;
    }
}
