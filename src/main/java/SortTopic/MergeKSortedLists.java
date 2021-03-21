package SortTopic;

import java.util.PriorityQueue;

/**
 * 23. 合并K个升序链表 Hard
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 *
 * 这题可以看成External Merge Sort 的 merge部分的简化，并且链表
 * 本身很容易找到下一个需要加入的元素，思考如果是数组怎么办，当然可以
 * 将每个元素添加一个标志，然后再加入优先队列，并且其实默认实现的优先队列
 * 并没有达到最优的效率，针对多个数组/模块合并，可以进行特定的改造，从而
 * 进一步提升效率。
 *
 * To Be Continue...
 */
public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((o1, o2) -> {return o1.val - o2.val;});
        ListNode result = new ListNode();
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }

        ListNode tail = result;
        while(!pq.isEmpty()) {
            tail.next = pq.poll();
            tail = tail.next;
            if (tail.next != null) {
                pq.offer(tail.next);
            }
        }
        return result.next;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
