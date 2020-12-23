package DynamicProgramming;

/**
 * 1314. 矩阵区域和 Median
 * https://leetcode-cn.com/problems/matrix-block-sum/
 *
 * 借鉴了 303. 区域和检索 - 数组不可变 这题的思路，这题是一维数组，任意位置求和
 * 所有我也是对mat的矩阵i行用dp[i][j]保存到第i行0-j的数字和，然后下面矩阵求和就是
 * 先划定范围，然后再一个for循环从上之下加起来就行，但是时间复杂度达到了O(n^3)，看题解
 * 是有二维前缀和的这个概念，可以O（n^2)解决问题。
 */
public class MatrixBlockSum {
    public int[][] matrixBlockSum(int[][] mat, int K) {
        int r = mat.length;
        int c = mat[0].length;
        int[][] dp = new int[r][c];

        for (int i = 0; i< r; i++) {
            int sum = 0;
            for (int j = 0; j < c; j++) {
                sum += mat[i][j];
                dp[i][j] = sum;
            }
        }

        int[][] result = new int[r][c];
        for (int i = 0; i <r; i++) {
            for (int j = 0; j < c; j++) {
                int left = Math.max(0, j - K);
                int right = Math.min(c-1, j + K);
                int up = Math.max(i - K, 0);
                int down = Math.min(r - 1, i + K);
                for (int k = up; k<= down; k++) {
                    result[i][j] += (dp[k][right] - dp[k][left] + mat[k][left]);
                }
            }
        }
        return result;
    }
}
