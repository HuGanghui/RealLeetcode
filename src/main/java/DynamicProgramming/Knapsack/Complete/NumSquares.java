package DynamicProgramming.Knapsack.Complete;

import java.util.Arrays;

/**
 * 279. 完全平方数 Median
 * https://leetcode-cn.com/problems/perfect-squares/
 *
 * 完全背包的最小值问题，用排列或者组合的递推方程都行，排列更简单，
 * 是一维的。
 */
public class NumSquares {
    public int numSquares(int n) {
        int sqrt = (int) Math.sqrt(n) + 1;
        int[] dp = new int[n+1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            for (int j = 1; j <= sqrt; j++) {
                int temp = j * j;
                if (i >= temp) {
                    dp[i] = Math.min(dp[i], dp[i-temp] + 1);
                }
            }
        }

        return dp[n];
    }

    // 组合的递推方程，同样OK
    public int numSquaresCombination(int n) {
        int k = (int) Math.sqrt(n) + 1;
        int[][] dp = new int[k+1][n+1];
        for (int i = 0; i <= k; i++) {
            Arrays.fill(dp[i], n);
        }
        for (int i = 0; i <= k; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= k; i++) {
            int square = i * i;
            for (int j = 1; j <= n; j++) {
                if (j >= square) {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-square] + 1);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[k][n];
    }
}
