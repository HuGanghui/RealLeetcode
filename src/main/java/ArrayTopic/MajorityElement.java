package ArrayTopic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 39. 数组中出现次数超过一半的数字 Easy
 * https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/
 *
 * 最直观的做法：就是用哈希来存储所有元素的个数，然后遍历获得答案，时间空间复杂度都是O(n)
 * 一个trick：因为这里众数的定义是超过一半并且题目保证一定存在，那么排序后，index位于中间
 * 的元素一定是答案
 * 更加巧妙的解法-摩尔投票法：核心理念为 票数正负抵消 。此方法时间和空间复杂度分别为O(n)，为本题的最佳解法。
 *
 */
public class MajorityElement {

    // 哈希
    public int majorityElement(int[] nums) {
        int max = 0;
        int result = -1;
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int cur = map.getOrDefault(nums[i], 0) + 1;
            map.put(nums[i], cur);
            if (cur > max) {
                max = cur;
                result = nums[i];
            }
        }
        return result;
    }

    // 排序 中间index
    public int majorityElementII(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    // 摩尔投票
    public int majorityElementIII(int[] nums) {
        int vote = 0;
        int x = 0;
        for (int num : nums) {
            if (vote == 0) {
                x = num;
            }
            if (x == num) {
                vote++;
            } else {
                vote--;
            }
        }
        return x;
    }
}
