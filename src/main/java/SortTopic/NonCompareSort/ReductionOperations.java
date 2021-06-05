package SortTopic.NonCompareSort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 5777. 使数组元素相等的减少操作次数 Median
 * https://leetcode-cn.com/problems/reduction-operations-to-make-the-array-elements-equal/
 * LeetcodeWeeklyMatch 244
 *
 * 这题思考后会发现，本质上就是类似计数排序的考察，从最大到第二小的每个元素，
 * 需要进行变换的次数就是比它小的数的key的数量 * 这个元素的所有的元素。
 *
 * 这题的一个trick就是它给的元素的大小范围是比较小的，小于 5 * 10^4。
 *
 */
public class ReductionOperations {
    public int reductionOperations(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
        }
        int size = map.keySet().size();
        int[] keys = new int[size];
        int i = 0;
        for (Integer key : map.keySet()) {
            keys[i] = key;
            i++;
        }
        Arrays.sort(keys);
        int result = 0;
        for (i = size - 1; i > 0; i--) {
            result += map.get(keys[i]) * i;
        }
        return result;
    }
}
