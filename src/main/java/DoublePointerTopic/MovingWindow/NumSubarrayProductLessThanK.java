package DoublePointerTopic.MovingWindow;

/**
 * 713. 乘积小于K的子数组 Median
 * https://leetcode-cn.com/problems/subarray-product-less-than-k/
 *
 * 暴力做法就是枚举，以nums中每个数为结尾/开头的看看有多少个符合条件，然后
 * 这种连续数组的满足条件的最优解法，一般就是滑动窗口，但是这题稍微有点特殊，
 * 在于是求乘积小于k的总和，因此需要每次在right作为结尾时，都记录一下总数。
 */
public class NumSubarrayProductLessThanK {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }
        int n = nums.length;
        int mul = 1;
        int left = 0;
        int right = 0;
        int size = 0;
        while (right < n) {
            int added = nums[right];
            mul *= added;
            right++;

            while (mul >= k) {
                mul /= nums[left];
                left++;
            }

            size += right - left;
        }
        return size;
    }
}
