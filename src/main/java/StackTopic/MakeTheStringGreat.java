package StackTopic;

import java.util.Stack;

/**
 * 1544. 整理字符串
 * https://leetcode-cn.com/problems/make-the-string-great/
 *
 * 核心——栈。
 */
public class MakeTheStringGreat {
    public String makeGood(String s) {
        Stack<Character> stack = new Stack<>();
        stack.push(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            Character ele = s.charAt(i);
            if (!stack.isEmpty() && Math.abs(stack.peek() - ele) == 32) {
                stack.pop();
            } else {
                stack.push(ele);
            }
        }

        String result = "";
        while (!stack.isEmpty()) {
            result = stack.pop() + result;
        }

        return result;

    }
}
