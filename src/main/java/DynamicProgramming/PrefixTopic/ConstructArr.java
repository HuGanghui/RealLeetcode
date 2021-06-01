package DynamicProgramming.PrefixTopic;

/**
 * 剑指 Offer 66. 构建乘积数组 Median
 * https://leetcode-cn.com/problems/gou-jian-cheng-ji-shu-zu-lcof/
 *
 * 前缀和的推广，变成求前缀乘积和后缀乘积，类似通过递推公式来减少计算，
 * prefix[i] = prefix[i-1] * num[i-1]，
 * suffix[i] = suffix[i+1] * a[i+1]。
 */
public class ConstructArr {
    public int[] constructArr(int[] a) {
        int n = a.length;
        if (n == 0) {
            return new int[0];
        }
        int[] prefix = new int[n];
        prefix[0] = 1;
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i-1] * a[i-1];
        }
        int[] suffix = new int[n];
        suffix[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i+1] * a[i+1];
        }
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = prefix[i] * suffix[i];
        }
        return result;
    }
}
