package DynamicProgramming.Knapsack.ZeroAndOne;

/**
 * 474. 一和零 Median
 * https://leetcode-cn.com/problems/ones-and-zeroes/
 *
 * 与经典的0-1背包相比，差别就是有两个背包容量的限制，其它区别不大。
 * value在这题里就是子集数量。
 */
public class FindMaxForm {
    public int findMaxForm(String[] strs, int m, int n) {
        int k = strs.length;
        int[][][] dp = new int[k+1][m+1][n+1];

        for (int i = 1; i <= k; i++) {
            int[] w = zeroOne(strs[i-1]);
            for (int j = 0; j <= m; j++) {
                for (int t = 0; t <= n; t++) {
                    if (w[0] <= j && w[1] <= t) {
                        dp[i][j][t] = Math.max(dp[i-1][j][t], dp[i-1][j-w[0]][t-w[1]]+1);
                    } else {
                        dp[i][j][t] = dp[i-1][j][t];
                    }
                }
            }
        }

        return dp[k][m][n];
    }

    private int[] zeroOne(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[] result = new int[2];
        for (int i = 0; i < n; i++) {
            if (chars[i] == '0') {
                result[0] += 1;
            } else {
                result[1] += 1;
            }
        }
        return result;
    }
}
