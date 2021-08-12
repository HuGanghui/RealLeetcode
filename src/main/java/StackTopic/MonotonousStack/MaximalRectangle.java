package StackTopic.MonotonousStack;

import java.util.*;

/**
 * 85. 最大矩形 Hard
 * https://leetcode-cn.com/problems/maximal-rectangle/
 *
 * 暴力方法是 枚举每个左上标和右下标，然后依次检查是否符合要求
 *
 * 巧妙解法：首先获取每行的以当前下标结尾的连续1的个数，相当于知道了
 * 一个柱子的高度，然后利用柱状图中最大的矩形的单调栈的方法，依次求
 * 每个列的最大矩形即可。
 *
 * 这样转换，就相当于是多个柱状图求最大而已，多了个遍历就是。
 */
public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        int n = matrix[0].length;

        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = dp[i][j-1] + 1;
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            Deque<Integer> deque = new LinkedList<>();
            int[] heights = new int[m + 2];
            heights[0] = 0;
            for (int j = 0; j < m; j++) {
                heights[j+1] = dp[j][i];
            }
            heights[m+1] = 0;
            int len = heights.length;
            deque.addLast(0);
            for (int k = 1; k < len; k++) {
                while (heights[k] < heights[deque.peekLast()]) {
                    int curHeight = heights[deque.pollLast()];
                    int curWidth = k - deque.peekLast() - 1;
                    res = Math.max(res, curHeight * curWidth);
                }
                deque.addLast(k);
            }
        }

        return res;
    }
}
