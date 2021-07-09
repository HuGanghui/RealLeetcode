package DynamicProgramming.Knapsack.ZeroAndOne;

/**
 * 494. 目标和 Median
 * https://leetcode-cn.com/problems/target-sum/
 *
 * 0-1背包的方案数问题
 */
public class FindTargetSumWays {
    public int findTargetSumWays(int[] nums, int S) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }

        if ((sum - S) % 2 != 0 || sum - S < 0) {
            return 0;
        }
        int w = (sum - S) / 2;
        int[][] dp = new int[n+1][w+1];
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= w; j++) {
                if (j >= nums[i-1]) {
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i-1]];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[n][w];
    }
}
