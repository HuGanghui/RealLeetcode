package StackTopic;

import java.util.Stack;

/**
 * 682. 棒球比赛
 * https://leetcode-cn.com/problems/baseball-game/
 *
 * 核心就是栈的使用。
 */
public class BaseballGame {
    public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < ops.length; i++) {
            if (ops[i].equals("+")) {
                int peekOne = stack.peek();
                stack.pop();
                int peekTwo = stack.peek();
                stack.push(peekOne);
                int result = peekOne + peekTwo;
                stack.push(result);
            } else if (ops[i].equals("D")) {
                int result = stack.peek() * 2;
                stack.push(result);
            } else if (ops[i].equals("C")) {
                stack.pop();
            } else  {
                stack.push(Integer.parseInt(ops[i]));
            }
        }
        int result = 0;
        for (int score : stack) {
            result += score;
        }
        return result;
    }
}
