package DynamicProgramming;

/**
 * 931. 下降路径最小和 Median
 * https://leetcode-cn.com/problems/minimum-falling-path-sum/
 *
 * 这题和大部分的路径问题都是类似的，使用DP也是显而易见的，只是这题做的时候，发现得
 * 当心正确获取最小值。
 */
public class MinFallingPathSum {
    public int minFallingPathSum(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) {
            dp[0][i] = A[0][i];
        }
        int[] incre = new int[] {-1, 0, 1};
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 这样也OK的
//                dp[i][j] = Integer.MAX_VALUE;
//                for (int ele : incre) {
//                    if (j+ele >= 0 && j+ele < n) {
//                        dp[i][j] = Math.min(dp[i-1][j+ele] + A[i][j], dp[i][j]);
//                    }
//                }
                int min = Integer.MAX_VALUE;
                for (int ele : incre) {
                    if (j+ele >= 0 && j+ele < n) {
                        min = Math.min(dp[i-1][j+ele], min);
                    }
                }
                dp[i][j] = min + A[i][j];
            }
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            result = Math.min(dp[m-1][i], result);
        }
        return result;
    }
}
