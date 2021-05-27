package SimulationTopic;

import java.util.Stack;

/**
 * 剑指 Offer 31. 栈的压入、弹出序列 Median
 * https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/
 *
 * 首先有个结论需要知道，就是给定pushed 和 poped序列，则压入 / 弹出操作的顺序（即排列）是 唯一确定的。
 * 因此验证的做法就是每次入栈后，比较栈首元素是否与当前poped的元素相同，如果相同则出栈，最后查看stack
 * 中是否还有元素。
 */
public class ValidateStackSequences {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int n = pushed.length;
        int j = 0;
        for (int i = 0; i < n; i++) {
            stack.push(pushed[i]);
            while(!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }
}
