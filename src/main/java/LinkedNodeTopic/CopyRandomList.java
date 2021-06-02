package LinkedNodeTopic;

import util.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 35. 复杂链表的复制 Median
 * https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof/
 *
 * 一般的链表节点复制，只需要两个指针，依次复制就行，而这题多了一个随机指针，导致
 * 迭代到随机指针时，可能该节点还没有被创建，因此利用哈希表，第一轮迭代先创建
 * 所有的新节点并做映射，第二轮迭代则完成所有的next和random指针的指向。
 */
public class CopyRandomList {
    public Node copyRandomList(Node head) {
        Node cur = head;
        Map<Node, Node> map = new HashMap<>();

        while (cur != null) {
            Node node = new Node(cur.val);
            map.put(cur, node);
            cur = cur.next;
        }
        for (Node node : map.keySet()) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
        }
        return map.get(head);
    }
}
