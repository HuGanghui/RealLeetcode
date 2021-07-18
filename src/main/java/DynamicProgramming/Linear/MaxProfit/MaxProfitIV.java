package DynamicProgramming.Linear.MaxProfit;

/**
 * 188. 买卖股票的最佳时机 IV Hard
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/
 *
 * 这题算是股票的问题的通解了，其它问题不过是相关变形而已，基本上就是通过三个状态
 * 的状态转移来完成DP，一个trick就是利用数组第0个作为base sample，方便完成init。
 */
public class MaxProfitIV {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;

        int[][][] dp = new int[n+1][k+1][2];

        for (int i = 0; i <= k; i++) {
            dp[0][i][0] = 0;
            dp[0][i][1] = Integer.MIN_VALUE;
        }

        for (int i = 0; i <= n; i++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = Integer.MIN_VALUE;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j][1] + prices[i-1]);
                dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][0] - prices[i-1]);
            }
        }
        return dp[n][k][0];
    }
}
