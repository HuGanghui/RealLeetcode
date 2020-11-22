package StackTopic;

import java.util.Stack;

/**
 * 503. 下一个更大元素 II Medium
 * https://leetcode-cn.com/problems/next-greater-element-ii/
 *
 * 和 1019. 链表中的下一个更大节点 Medium 是基本一致的思想，就是循环数组，需要再过一遍。
 */
public class NextGreaterElement {
    public int[] nextGreaterElements(int[] nums) {
        Stack<Pair> stack = new Stack<>();
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] > stack.peek().value) {
                result[stack.pop().index] = nums[i];
            }
            Pair pair = new Pair(nums[i], i);
            stack.push(pair);
            result[i] = 0;
        }

        if (!stack.isEmpty()) {
            while (!stack.isEmpty()) {
                for (int i = 0; i < nums.length; i++) {
                    while (!stack.isEmpty() && nums[i] > stack.peek().value) {
                        result[stack.pop().index] = nums[i];
                    }
                }
            }
        }

        return result;


    }
}

class Pair {
    int value;
    int index;

    public Pair(int value , int index) {
        this.value = value;
        this.index = index;
    }
}
