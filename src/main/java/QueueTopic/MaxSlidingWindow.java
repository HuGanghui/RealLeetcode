package QueueTopic;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 剑指 Offer 59 - I. 滑动窗口的最大值 Easy --
 * https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/
 *
 * 这题暴力解法很简单时间复杂度O(kn),O(n)的方法思想可以参考"队列的最大值"，但稍微更复杂些，
 * 分形成窗口和未形成窗口两个阶段，并且保证了队首元素就是当前窗口的最大值。
 */
public class MaxSlidingWindow {
    Deque<Integer> queue = new ArrayDeque<>();

    public int[] maxSlidingWindow(int[] nums, int k) {

        if(nums.length == 0 || k == 0) {
            return new int[0];
        }

        int[] result = new int[nums.length-(k-1)];
        for (int i = 0; i < nums.length; i++) {
            if (i > (k-1)) {
                if (nums[i - (k  - 1)] == queue.peekFirst()) {
                    queue.remove();
                }
                while (!queue.isEmpty() && queue.peekLast() < nums[i]) {
                    queue.removeLast();
                }
                queue.addLast(nums[i]);
                result[i - (k - 1)] = queue.peekFirst();
            } else {
                while (!queue.isEmpty() && queue.peekLast() < nums[i]) {
                    queue.removeLast();
                }
                queue.addLast(nums[i]);
                if (i == k - 1) {
                    result[0] = queue.peek();
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        MaxSlidingWindow maxSlidingWindow = new MaxSlidingWindow();
        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        maxSlidingWindow.maxSlidingWindow(nums, 3);
    }
}
