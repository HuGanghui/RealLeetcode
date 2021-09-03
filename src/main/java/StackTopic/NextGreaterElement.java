package StackTopic;

import java.util.*;

/**
 * 503. 下一个更大元素 II Medium
 * https://leetcode-cn.com/problems/next-greater-element-ii/
 *
 * 和 1019. 链表中的下一个更大节点 Medium 是基本一致的思想，就是循环数组，需要再过一遍。
 */
public class NextGreaterElement {
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        for (int i = 0; i < n; i++) {
            while(!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                result[stack.pop()] = nums[i];
            }
            stack.push(i);
        }
        // 再来一遍
        if (!stack.isEmpty()) {
            for (int i = 0; i < n; i++) {
                while(!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                    result[stack.pop()] = nums[i];
                }
            }
        }
        return result;
    }
}
