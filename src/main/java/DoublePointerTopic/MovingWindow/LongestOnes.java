package DoublePointerTopic.MovingWindow;

/**
 * 1004. 最大连续1的个数 III Median
 * https://leetcode-cn.com/problems/max-consecutive-ones-iii/
 *
 * 这种连续的题，都非常适合用滑动窗口来解决，不过这题需要有个转换，就是
 * 可以将k个0变成1，其实就是滑动窗口中最多可以有k个0。
 */
public class LongestOnes {
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int oneSize = 0;
        int left = 0;
        int right = 0;
        int len = 0;
        while (right < n) {
            int cur = nums[right];
            right++;
            if (cur == 0) {
                oneSize++;
            }
            if (oneSize <= k) {
                len = Math.max(len, right - left);
            } else {
                while (nums[left] != 0) {
                    left++;
                }
                left++;
                oneSize--;
            }
        }
        return len;
    }
}
