package DynamicProgramming.TransformOpt;

import java.util.*;

/**
 * 974. 和可被 K 整除的子数组 Median
 * https://leetcode-cn.com/problems/subarray-sums-divisible-by-k/
 *
 * 不难想到前缀和，但是只求数量，不求具体的解，因此可以进一步优化，通过
 * 公式的变形，然后结合哈希表。
 *
 * 公式：(prefix[j] - prefix[i]) % k == 0 可转变为 prefix[j] % k == prefix[i] % k
 */
public class SubarraysDivByK {
    public int subarraysDivByK(int[] nums, int k) {
        // (prefix[j] - prefix[i]) % k == 0;
        // prefix[j] % k == prefix[i] % k

        int n = nums.length;
        int[] prefix = new int[n];
        prefix[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i-1] + nums[i];
        }
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        for (int i = 0; i < n; i++) {
            int left = (prefix[i] % k + k) % k;
            // int left = prefix[i] % k ;
            if (map.containsKey(left)) {
                result += map.get(left);
            }
            map.put(left, map.getOrDefault(left, 0) + 1);
        }

        for (int i = 0; i < n; i++) {
            if (prefix[i] % k == 0) {
                result++;
            }
        }

        return result;
    }
}
