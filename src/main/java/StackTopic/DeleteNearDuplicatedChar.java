package StackTopic;

import java.util.Stack;

/**
 * 1047. 删除字符串中的所有相邻重复项
 * https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string/
 *
 * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 *
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 *
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 *
 * 核心还是栈，我就使用了Stack，但是不一定需要真正的栈，有进出的理念就行。
 */
public class DeleteNearDuplicatedChar {
    public String removeDuplicates(String S) {
        Stack stack = new Stack();
        stack.push(S.substring(0, 1));
        for (int i = 1; i < S.length(); i++) {
            String ele = S.substring(i, i+1);
            if (!stack.isEmpty() && stack.peek().equals(ele)) {
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
