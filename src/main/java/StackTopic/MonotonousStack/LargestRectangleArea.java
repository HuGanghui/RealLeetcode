package StackTopic.MonotonousStack;

import java.util.*;

/**
 * 84. 柱状图中最大的矩形 Hard
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 *
 * 这题的暴力解法，就是枚举每个高度，然后往左右两边扩展到比其低的，然后计算最大值，
 * 时间复杂度 O(n^2) 超时
 *
 * 更巧妙的解法，单调栈，利用下标就是宽度，栈是一个递增的栈，遇到第一个高度小于当前
 * 栈顶元素则栈顶元素可以进行面积计算了，时间复杂度 O(n)。
 */
public class LargestRectangleArea {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;

        if (n == 0) {
            return 0;
        }

        // 增加头尾两个哨兵，这样可以有效避免去判断边界条件
        // 比如栈为空，计算最后一个节点等
        int[] newHeight = new int[n+2];
        newHeight[0] = 0;
        for (int i = 0; i < n; i++) {
            newHeight[i+1] = heights[i];
        }
        newHeight[n+1] = 0;

        int len = n + 2;
        Deque<Integer> deque = new LinkedList<>();
        deque.addLast(0);

        int res = 0;
        for (int i = 1; i < len; i++) {
            while (newHeight[i] < newHeight[deque.peekLast()]) {
                int curHeight = newHeight[deque.pollLast()];
                int curWidth = i - deque.peekLast() - 1;
                res = Math.max(res, curHeight * curWidth);
            }
            deque.addLast(i);
        }

        return res;
    }

    // O(n^2)
    public int largestRectangleArea_Brute(int[] heights) {
        int n = heights.length;
        int result = 0;

        for (int i = 0; i < n; i++) {

            int cur = heights[i];
            int width = 0;
            int left = i-1;
            while (left >= 0 && heights[left] >= cur) {
                left--;
            }

            int right = i+1;
            while (right < n && heights[right] >= cur) {
                right++;
            }
            width = right - left - 1;
            result = Math.max(result, width * cur);

        }

        return result;
    }
}
