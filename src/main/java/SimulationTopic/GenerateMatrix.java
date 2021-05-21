package SimulationTopic;

/**
 * 59. 螺旋矩阵 II Median
 * https://leetcode-cn.com/problems/spiral-matrix-ii/
 *
 * 这题和螺旋矩阵/剑指 Offer 29. 顺时针打印矩阵 基本是一个套路，
 * 核心在于如何进行矩阵的遍历，这个明确好了，之后无论是访问还是构造
 * 就是换个形式而已。
 */
public class GenerateMatrix {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int top = 0, bottom = n - 1;
        int left = 0, right = n - 1;
        int num = 1;
        while (top <= bottom && left <= right) {
            for (int column = left; column <= right; column++) {
                matrix[top][column] = num;
                num++;
            }
            for (int row = top + 1; row <= bottom; row++) {
                matrix[row][right] = num;
                num++;
            }
            if (top < bottom && left < right) {
                for (int column = right - 1; column > left; column--) {
                    matrix[bottom][column] = num;
                    num++;
                }
                for (int row = bottom; row > top; row--) {
                    matrix[row][left] = num;
                    num++;
                }
            }
            top++;
            bottom--;
            left++;
            right--;
        }
        return matrix;
    }
}
