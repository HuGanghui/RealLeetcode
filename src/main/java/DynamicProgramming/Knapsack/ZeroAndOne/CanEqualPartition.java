package DynamicProgramming.Knapsack.ZeroAndOne;

/**
 * 416. 分割等和子集 Median
 * https://leetcode-cn.com/problems/partition-equal-subset-sum/
 *
 * 0-1背包问题的变体，不求最大max，而是求是否可以true/false，有人称为0-1背包可行性问题。
 */
public class CanEqualPartition {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;

        boolean[][] dp = new boolean[n+1][target+1];
        dp[0][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                if (j >= nums[i-1]) {
                    dp[i][j] = dp[i-1][j] | dp[i-1][j-nums[i-1]];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][target];

    }
}
