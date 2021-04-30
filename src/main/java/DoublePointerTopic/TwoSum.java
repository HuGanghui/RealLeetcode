package DoublePointerTopic;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和 Easy
 * https://leetcode-cn.com/problems/two-sum/
 *
 * 这题和NSum略有不同，就是要求需要返回的是下标，因此
 * 无论是排序还是hash表，都需要将元素和下标绑定到一起，方便
 * 后面的输出结果。
 *
 * 并且下面的hash表解法有个小的trick，就是为了避免重复解以及
 * diff等于当前元素，只用该元素之前的那些在hash表中的元素，而不是
 * 一开始就全部放入。并且题目保证了只要一个解，如果也需要保证没有重复的value解，
 * 那还是要排序的。
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (map.containsKey(diff)) {
                return new int[]{i, map.get(diff)};
            }
            map.put(nums[i], i);
        }
        return result;
    }
}
