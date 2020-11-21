package StackTopic;

import java.util.Stack;

/**
 * 844. 比较含退格的字符串
 * https://leetcode-cn.com/problems/backspace-string-compare/
 *
 * 栈解法，还有另外一种是双指针，可以降低空间复杂度。
 */
public class BackspaceStringCompare {
    public boolean backspaceCompare(String S, String T) {
        Stack<String> stackOne = newString(S);
        Stack<String> stackTwo = newString(T);
        if (stackOne.size() != stackTwo.size()) {
            return false;
        } else {
            while (!stackOne.isEmpty()) {
                if (!stackOne.pop().equals(stackTwo.pop())) {
                    return false;
                }
            }
            return true;
        }
    }

    private Stack<String> newString(String S) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i< S.length(); i++) {
            String ele = S.substring(i, i+1);
            if (ele.equals("#")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(ele);
            }
        }
        return stack;
    }
}
