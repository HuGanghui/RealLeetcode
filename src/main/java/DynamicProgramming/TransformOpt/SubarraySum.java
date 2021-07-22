package DynamicProgramming.TransformOpt;

import java.util.*;

/**
 * 560. 和为K的子数组 Median
 * https://leetcode-cn.com/problems/subarray-sum-equals-k/
 *
 * 不难想到前缀和，但是只求数量，不求具体的解，因此可以进一步优化，通过
 * 公式的变形，然后结合哈希表。
 *
 * 公式：prefix[j] - prefix[i] == k 可转变为 prefix[j] - k = prefix[i]
 */
public class SubarraySum {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] prefix = new int[n];
        prefix[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i-1] + nums[i];
        }

        // prefix[j] - prefix[i] + nums[i] == k
        // prefix[j] - k = prefix[i] - nums[i]

        Map<Integer, Integer> map = new HashMap<>();

        int result = 0;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(prefix[i] - k)) {
                result += map.get(prefix[i] - k);
            }
            map.put(prefix[i], map.getOrDefault(prefix[i], 0) + 1);
        }

        for (int i = 0; i < n; i++) {
            if (prefix[i] == k) {
                result++;
            }
        }

        return result;
    }
}
