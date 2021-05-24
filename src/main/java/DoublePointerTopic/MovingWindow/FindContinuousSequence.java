package DoublePointerTopic.MovingWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 57 - II. 和为s的连续正数序列 Easy
 * https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/
 *
 * 这题的暴力方法便是枚举以每个元素为起点，然后求和时间复杂度O(n^2)，但是利用滑动窗口的性质，我们可以以
 * O(n)的时间复杂度完成，主要就是利用了当l-r == target, l+1-r < target的性质，因此能够获得全部的解，
 * 然后滑动窗口最关键的点在于弄清楚窗口何时扩大，何时缩小。对于本题，显然是当小于target时扩大，大于等于时缩小。
 */
public class FindContinuousSequence {
    public int[][] findContinuousSequence(int target) {
        int left = 1;
        int right = 1;
        List<int[]> lists = new ArrayList<>();
        while (right < target) {
            int sum = getWindowSum(left, right);
            if (sum == target) {
                int len = right - left + 1;
                int[] temp = new int[len];
                int i = 0;
                while (i < len) {
                    temp[i] = i + left;
                    i++;
                }
                lists.add(temp);
                left++;
            } else if (sum > target){
                left++;
            } else {
                right++;
            }
        }
        return lists.toArray(new int[lists.size()][]);
    }

    private int getWindowSum(int start, int end) {
        return (start + end) * (end - start + 1) / 2;
    }
}
