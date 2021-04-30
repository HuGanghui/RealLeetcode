package DoublePointerTopic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18. 四数之和 Median
 * https://leetcode-cn.com/problems/4sum/
 *
 * 15. 三数之和 Median
 * https://leetcode-cn.com/problems/3sum/
 *
 * 参考labuladong文章-解决NSum问题：https://mp.weixin.qq.com/s/fSyJVvggxHq28a0SdmZm6Q
 *
 * 上面两道题其实总结一下就是NSum问题，根本是TwoSum，其他的就依次递归就行。
 * 然后注意到题目要求不能有重复的解，这就要进行排序了，如果不排序，很难不重复，其实这个之前在练习回溯
 * 题目的时候，也遇到过，先排序，然后跳过。
 *
 */
public class NSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return nSum(nums, 4, 0, target);
    }

    private List<List<Integer>> nSum(int[] nums, int n, int startIndex, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (n < 2 || nums.length < n) {
            return result;
        }
        if (n == 2) {
            int left = startIndex;
            int right = nums.length - 1;
            while (left < right) {
                int leftNum = nums[left];
                int rightNum = nums[right];
                int sum = leftNum + rightNum;
                if (sum == target) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(leftNum);
                    temp.add(rightNum);
                    result.add(temp);
                    // 跳过相同元素
                    while(left < right && nums[left] == leftNum) {
                        left++;
                    }
                    while(left < right && nums[right] == rightNum) {
                        right--;
                    }
                } else if (sum < target) {
                    // 跳过相同元素
                    while(left < right && nums[left] == leftNum) {
                        left++;
                    }
                } else {
                    // 跳过相同元素
                    while(left < right && nums[right] == rightNum) {
                        right--;
                    }
                }
            }
        } else {
            for (int i = startIndex; i < nums.length; i++) {
                List<List<Integer>> subResult = nSum(nums, n-1, i+1, target - nums[i]);
                for (List<Integer> ele : subResult) {
                    ele.add(nums[i]);
                    result.add(ele);
                }
                // 跳过相同元素
                while(i < nums.length -1 && nums[i+1] == nums[i]) {
                    i++;
                }
            }
        }
        return result;
    }
}
