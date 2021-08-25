package StringTopic;

import java.util.*;

/**
 * 224. 基本计算器 Hard
 * https://leetcode-cn.com/problems/basic-calculator/
 *
 * 由于只有加减和括号，因此要处理的其实就是括号和符号的问题，通过一个栈来保存目前的符号，
 * 这样后续处理的话就可以直接计算并放到结果res中去。
 */
public class Calculate {
    public int calculate(String s) {
        Stack<Integer> sign = new Stack<>();
        sign.push(1);

        int res = 0;
        int num = 0;
        int op = 1;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            char cur = s.charAt(i);
            if (cur >= '0' && cur <= '9') {
                num = num * 10 + (cur - '0');
                continue;
            }

            res += op * num;
            num = 0;

            if (cur == '+') {
                op = sign.peek();
            } else if (cur == '-') {
                op = -sign.peek();
            } else if (cur == '(') {
                sign.push(op);
            } else if (cur == ')') {
                sign.pop();
            }
        }
        res += op * num;
        return res;
    }
}
