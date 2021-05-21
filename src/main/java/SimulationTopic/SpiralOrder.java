package SimulationTopic;

/**
 * 剑指 Offer 29. 顺时针打印矩阵 Easy
 * https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/
 *
 * 这里使用的方法题解的按层模拟，依次通过
 * 1.（top, left）-> (top, right）
 * 2. (top + 1, right) -> (bottom, right)
 * 3. 如果 left < right && top < bottom，则遍历从右到左遍历下侧元素，在从下到上遍历左侧元素
 *    (bottom, right - 1) -> (bottom, left+1)
 *    (bottom, left) -> (top + 1, left)
 * 遍历完成后进行 left top ++ right bottom --，接着继续遍历。
 */
public class SpiralOrder {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[] order = new int[rows * columns];
        int index = 0;
        int left = 0, right = columns - 1;
        int top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int column = left; column <= right; column++) {
                order[index++] = matrix[top][column];
            }
            for (int row = top + 1; row <= bottom; row++) {
                order[index++] = matrix[row][right];
            }
            if (left < right && top < bottom) {
                for (int column = right - 1; column > left; column--) {
                    order[index++] = matrix[bottom][column];
                }
                for (int row = bottom; row > top; row--) {
                    order[index--] = matrix[row][left];
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }
}
