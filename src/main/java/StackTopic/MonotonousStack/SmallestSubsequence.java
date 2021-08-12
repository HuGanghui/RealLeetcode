package StackTopic.MonotonousStack;

import java.util.*;

/**
 * 1081. 不同字符的最小子序列 Median
 * https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters/
 *
 * 在单调栈的基础上，再加两点，一个是已经存在在栈中的，不能再添加，另一个是如果后面没有栈顶元素了，
 * 那栈顶元素不能再弹出。
 */
public class SmallestSubsequence {
    public String smallestSubsequence(String s) {
        int n = s.length();
        boolean[] exists = new boolean[26];
        int[] leftNum = new int[26];
        for (int i = 0; i < n; i++) {
            leftNum[s.charAt(i) - 'a'] += 1;
        }

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            char cur = s.charAt(i);
            if (!exists[cur - 'a']) {
                while (!stack.isEmpty() && stack.peek() > cur && leftNum[stack.peek() - 'a'] > 0) {
                    char removed = stack.pop();
                    exists[removed - 'a'] = false;
                }
                exists[cur - 'a'] = true;
                stack.push(cur);
            }
            leftNum[cur - 'a'] -= 1;
        }

        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }
        return builder.reverse().toString();
    }
}
