package DynamicProgramming.Knapsack.Complete;

/**
 * 518. 零钱兑换 II Median
 * https://leetcode-cn.com/problems/coin-change-2/
 *
 * 这就是完全背包组合方案数问题，是一个组合问题。
 */
public class CoinChangeII {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int m = amount;
        int[][] dp = new int[n+1][m+1];

        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (j >= coins[i-1]) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][m];
    }
}
