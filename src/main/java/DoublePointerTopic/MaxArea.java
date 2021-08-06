package DoublePointerTopic;

/**
 * 11. 盛最多水的容器 Median
 * https://leetcode-cn.com/problems/container-with-most-water/
 *
 * 这题可能比较难想到双指针的正确性，但确实是双指针，因为较小的如果不移动，
 * 得到的值只会更小。
 */
public class MaxArea {
    public int maxArea(int[] height) {
        int n = height.length;
        int left = 0;
        int right = n - 1;
        int result = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                result = Math.max(result, height[left] * (right - left));
                left++;
            } else {
                result = Math.max(result, height[right] * (right - left));
                right--;
            }
        }
        return result;
    }
}
