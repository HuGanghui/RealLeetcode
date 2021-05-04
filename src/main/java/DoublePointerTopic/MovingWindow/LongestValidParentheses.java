package DoublePointerTopic.MovingWindow;

/**
 * 32. 最长有效括号 Hard
 * https://leetcode-cn.com/problems/longest-valid-parentheses/
 *
 * 这题因为求的是最长子串，所以可以使用滑动窗口的思想来做，合适进行缩减，
 * 便是遇到左括号数量小于右括号数量时，因为有效括号的一个标志便是从左到右必然左括号数量一直大于等于右括号，
 * 缩减便是把之前的积累的左右括号数量全部归零，相当于从头开始，因为都是非法的了。记录最大值，但是有个问题
 * 就是如果只在left_count == right_count时记录，对于'(()'这种情况，就无法记录到，然后
 * 题解提到可以反过来再进行一遍取最大值，这个应该是利用了有效括号的对称性，来弥补这种情况。
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int s_length = s.length();
        int left = 0;
        int right = 0;
        int left_count = 0;
        int right_count = 0;
        int len = 0;
        while(right < s_length) {
            char added = s.charAt(right);
            right++;
            if (added == '(') {
                left_count++;
            } else {
                right_count++;
            }
            if (left_count == right_count) {
                len = Math.max(len, left_count);
            }
            if (left_count < right_count) {
                left = right;
                left_count = 0;
                right_count = 0;
            }
        }

        left = s_length-1;
        right = s_length-1;
        left_count = 0;
        right_count = 0;
        while(right > -1) {
            char added = s.charAt(right);
            right--;
            if (added == ')') {
                left_count++;
            } else {
                right_count++;
            }
            if (left_count == right_count) {
                len = Math.max(len, left_count);
            }
            if (left_count < right_count) {
                left = right;
                left_count = 0;
                right_count = 0;
            }
        }
        return len * 2;
    }
}
