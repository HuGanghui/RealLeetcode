package DynamicProgramming.Linear;

/**
 * 5815. 扣分后的最大得分 Median
 * https://leetcode-cn.com/problems/maximum-number-of-points-with-cost/
 * LeetcodeWeeklyMatch 250
 *
 * 直观上可以了解到当前行要得到最大分数，只与上一行相关，因此可以利用DP来完成，不过
 * 这题需要进行一定的优化，将时间复杂度从O(RC^2)降低到O(RC)。优化的方法还是通过
 * 公式的化简，然后复用。
 */
public class MaxPoints {
    public long maxPoints(int[][] points) {
        int m = points.length;
        int n = points[0].length;
        long[][] dp = new long[m][n];

        for (int i = 0;  i< n; i++) {
            dp[0][i] = points[0][i];
        }

        for (int i = 1; i < m; i++) {
            long[] lmax = new long[n];
            lmax[0] = dp[i-1][0];
            for (int j = 1; j < n; j++) {
                // dp[i-1][k] + k - j + points[i][j]
                lmax[j] = Math.max(dp[i-1][j] + j, lmax[j-1]);
            }

            long[] rmax = new long[n];
            rmax[n-1] = dp[i-1][n-1] - (n - 1);
            for (int j = n-2; j >= 0; j--) {
                rmax[j] = Math.max(dp[i-1][j] - j, rmax[j+1]);
            }

            for (int j = 0; j < n; j++) {
                dp[i][j] = Math.max(lmax[j] - j + points[i][j], rmax[j] + j + points[i][j]);
            }
        }

        long result = 0;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, dp[m-1][i]);
        }

        return result;
    }

    // 最朴素的DP，但是会超时，因为第三层循环需要遍历整个上一行，通过公式
    // 的转换，可以发现每行可以复用的地方，从而可以进行前后缀的化简。
    public long maxPoints_NaiveDP(int[][] points) {
        int m = points.length;
        int n = points[0].length;
        long[][] dp = new long[m][n];

        for (int i = 0;  i< n; i++) {
            dp[0][i] = points[0][i];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    dp[i][j] = Math.max(dp[i-1][k] + points[i][j] - Math.abs(k - j), dp[i][j]);
                }
            }
        }

        long result = 0;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, dp[m-1][i]);
        }

        return result;
    }
}
