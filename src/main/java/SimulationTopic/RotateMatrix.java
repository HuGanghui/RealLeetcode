package SimulationTopic;

/**
 * 48. 旋转图像 Median
 * https://leetcode-cn.com/problems/rotate-image/
 *
 * 这题的核心就是找到旋转前后每个元素的对应下标，这题是顺时针旋转90度，
 * 那就前后对比可以找到m[row][col] -> m[col][n - row - 1]。
 *
 * 接下来就是如何得到这个变换了，顺时针旋转90度对应的一种方式就是先上下翻转：
 * m[row][col] -> m[n - row - 1][col]
 * 然后对角线翻转：m[n - row - 1][col] -> m[col][n - row - 1]。
 * 如果是其它角度比如180度或者逆时针，也是类似分析，先找到最终的变换情况，
 * 然后思考如何变换过去，常用手段就是变旋转为翻转。
 */
public class RotateMatrix {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 水平翻转
        for(int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
        // 主对角线翻转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}
