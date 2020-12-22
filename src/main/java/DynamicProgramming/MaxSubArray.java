package DynamicProgramming;

/**
 * 剑指 Offer 42. 连续子数组的最大和 Easy
 * https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/
 *
 * 经典的动态规划子序列问题，以先找每个位置结尾的最大子数组，然后取其中的最大值。题解还有
 * 其它不错的解法，可以回头参考。
 */
public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        int result = Integer.MIN_VALUE;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        result = dp[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i-1] < 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = dp[i-1] + nums[i];
            }
            if (dp[i] > result) {
                result = dp[i];
            }
        }
        return result;
    }
}
