package DoublePointerTopic;

import java.util.*;

/**
 * 16. 最接近的三数之和 Median
 * https://leetcode-cn.com/problems/3sum-closest/
 *
 * 和三数之和类似的做法
 */
public class ThreeSumClosest {
    private int res;

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        // 初始化一个答案
        res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < n-2; i++) {
            int cur = nums[i];
            if (i > 0 && cur == nums[i-1]) {
                continue;
            }
            int diff = target - cur;
            twoSumClosest(nums, i+1, diff, cur);
        }
        return res;
    }

    private void twoSumClosest(int[] nums, int index, int diff, int cur) {
        int left = index;
        int right = nums.length - 1;
        while (left < right) {
            int leftNum = nums[left];
            int rightNum = nums[right];
            int sum = leftNum + rightNum + cur;

            if (Math.abs(sum - diff - cur) < Math.abs(res - diff - cur)) {
                res = sum;
            }
            if (sum > diff + cur) {
                while (right > left && nums[right] == rightNum) {
                    right--;
                }
            } else {
                while (left < right && nums[left] == leftNum) {
                    left++;
                }
            }
        }
    }
}
