package QueueTopic;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer 59 - I. 滑动窗口的最大值 Hard
 * https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/
 *
 * 这题暴力解法很简单，时间复杂度O(kn),O(n)的方法思想可以参考"队列的最大值"，但稍微更复杂些，
 * 分形成窗口和未形成窗口两个阶段，并且保证了队首元素就是当前窗口的最大值。
 */
public class MaxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        int n = nums.length;
        if (n == 0) {
            return new int[0];
        }
        int[] result = new int[n - k + 1];
        for (int i = 0; i < n; i++) {
            // 初始化还未到滑窗大小
            if (i < k - 1) {
                while (!deque.isEmpty() && nums[i] > deque.peekLast()) {
                    deque.removeLast();
                }
                deque.addLast(nums[i]);
            } else {
                if (i != k - 1) {
                    // 先出队，判断是否与队首元素相等
                    int removed = nums[i - k];
                    if (deque.peekFirst() == removed) {
                        deque.removeFirst();
                    }
                }
                // 然后按规则加入单调队列
                while (!deque.isEmpty() && nums[i] > deque.peekLast()) {
                    deque.removeLast();
                }
                deque.addLast(nums[i]);
                result[i - k + 1] = deque.peekFirst();
            }
        }
        return result;
    }
}
