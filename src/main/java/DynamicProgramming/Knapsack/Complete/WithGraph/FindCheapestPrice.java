package DynamicProgramming.Knapsack.Complete.WithGraph;

import java.util.*;

/**
 * 787. K 站中转内最便宜的航班 Median
 * https://leetcode-cn.com/problems/cheapest-flights-within-k-stops/
 *
 * 这题和1928. 规定时间内到达终点的最小花费做法基本上是一致的，完全背包结合图来进行处理。
 */
public class FindCheapestPrice {
    private final int MAX = (int)Math.pow(10, 5);

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // dp[k][i] = min(dp[k-1][j])
        // 取 dp[1...k][dst] 的最小值
        int[][] dp = new int[k+2][n];

        Arrays.fill(dp[0], MAX);
        dp[0][src] = 0;

        for (int i = 1; i <= k+1; i++) {
            Arrays.fill(dp[i], MAX);
            for (int[] flight : flights) {
                int from = flight[0];
                int to = flight[1];
                int cost = flight[2];
                dp[i][to] = Math.min(dp[i-1][from] + cost, dp[i][to]);
            }
        }

        int res = dp[0][dst];
        for (int i = 1; i<= k+1; i++) {
            res = Math.min(res, dp[i][dst]);
        }
        return res == MAX ? -1 : res;
    }
}
