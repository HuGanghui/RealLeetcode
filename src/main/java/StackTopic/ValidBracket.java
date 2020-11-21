package StackTopic;

import java.util.Stack;

/**
 * 20. 有效的括号
 * https://leetcode-cn.com/problems/valid-parentheses/
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 核心就是栈的使用，保证不多不少。
 */
public class ValidBracket {

    public final String small = "(";
    public final String medium = "[";
    public final String large = "{";

    public boolean isValid(String s) {
        Stack stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            String str = s.substring(i, i+1);
            if (str.equals(small) || str.equals(large) || str.equals(medium)) {
                stack.push(str);
            } else if (str.equals(")")) {
                if (!stack.isEmpty() && stack.peek().equals(small)) {
                    stack.pop();
                } else {
                    return false;
                }
            } else if (str.equals("]")) {
                if (!stack.isEmpty() && stack.peek().equals(medium)) {
                    stack.pop();
                } else {
                    return false;
                }
            } else if (str.equals("}")) {
                if (!stack.isEmpty() && stack.peek().equals(large)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        ValidBracket validBracket = new ValidBracket();
        validBracket.isValid("(]");
    }
}
