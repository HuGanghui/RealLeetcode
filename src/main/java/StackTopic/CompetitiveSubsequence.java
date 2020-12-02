package StackTopic;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 1673. 找出最具竞争力的子序列 Median
 * https://leetcode-cn.com/problems/find-the-most-competitive-subsequence/
 *
 * 有点类似队列最大值的做法，但是有个边界条件就是当剩下的数组元素无法保证最后能有k个元素时，就不能再出队/出栈了。
 */
public class CompetitiveSubsequence {
    Deque<Integer> deque = new LinkedList<>();

    public int[] mostCompetitive(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && deque.peekLast() > nums[i]
                    && (k - deque.size()) < nums.length - i){
                deque.removeLast();
            }
            deque.addLast(nums[i]);
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = deque.removeFirst();
        }
        return result;
    }

    public static void main(String[] args) {
        CompetitiveSubsequence competitiveSubsequence = new CompetitiveSubsequence();
        competitiveSubsequence.mostCompetitive(new int[] {3,5,2,6,2}, 2);
    }
}
