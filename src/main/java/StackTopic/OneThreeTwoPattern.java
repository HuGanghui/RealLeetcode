package StackTopic;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 456. 132模式 Median
 * https://leetcode-cn.com/problems/132-pattern/
 *
 * 这题也有点复杂了。
 * 参考题解 https://leetcode-cn.com/problems/132-pattern/solution/zhan-jie-fa-chao-xiang-xi-ti-jie-by-siyy/
 * 这题有很多值得考究的点：
 * 1. 对于题目要求的子序列符合 ai < ak < aj, 其中 i<j<k，首先将问题学会转化为找到一个元素 aj,
 * 在区间[1, j-1]里有比他小的元素M1，在区间[j+1, n]里也有比他小的元素M2, 并且M2>M1，因此首先找的
 * [1, j-1]里的最小值M1
 * 2. 然后我们可以暴力求解了，复杂度O(n^2), 比较难想的就是可以从数组尾部开始，委会一个单调递减的栈，要求对a[j], 栈内元素
 * 必须大于对应M1，否则就出栈，然后比较栈顶元素和a[j], 如果栈顶元素 < a[j], 那我们就找的了，不然就入栈，符合单减栈的要求
 * 3. 在2.隐藏了一个点就是M1所在数组也是单减的，因此对于栈内元素大于对应M1，否则就出栈是不影响后面的。
 */
public class OneThreeTwoPattern {
    public boolean find132pattern(int[] nums) {
        Deque<Integer> deque = new LinkedList<>();

        int[] min = new int[nums.length];
        min[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min[i] = Math.min(min[i-1], nums[i]);
        }
        for (int j = nums.length -1; j >= 1; j--) {
            if (nums[j] > min[j]) {
                while (!deque.isEmpty() && deque.peekLast() <= min[j]) {
                    deque.removeLast();
                }

                if (!deque.isEmpty() && deque.peekLast() < nums[j]) {
                    return true;
                }
                deque.addLast(nums[j]);
            }
        }
        return false;

    }

    public static void main(String[] args) {
        OneThreeTwoPattern oneThreeTwoPattern = new OneThreeTwoPattern();
        boolean result = oneThreeTwoPattern.find132pattern(new int[] {3, 5, 0, 3, 4});
        System.out.println(result);
    }
}
