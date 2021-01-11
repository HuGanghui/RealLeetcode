package LeetcodeWeeklyMatch.DoubleWeekly43;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 1717. 删除子字符串的最大得分 Median
 * https://leetcode-cn.com/problems/maximum-score-from-removing-substrings/
 *
 * 一看到题目就知道需要有一定贪心的思想，那个分数大，先处理哪个；然后对于字符串这种比较的，显然
 * 就是需要使用栈来处理，这个都是套路了。可能是比赛的时候状态不好，总是有错，临场表现还想不行。
 */
public class MaxScoreFromRemovingSubstrings {
    private int result = 0;
    public int maximumGain(String s, int x, int y) {
        char[] chars = s.toCharArray();
        if (x > y) {
            char[] temp = getScore(chars, x, 'a', 'b');
            getScore(temp, y, 'b', 'a');
        } else {
            char[] temp = getScore(chars, y, 'b', 'a');
            getScore(temp, x, 'a', 'b');
        }
        return result;
    }

    private char[] getScore(char[] chars, int score, char fir, char sec) {
        Deque<Character> deque = new LinkedList<>();
        for (int i = 0; i < chars.length; i++) {
            if(!deque.isEmpty() && deque.peekLast() == fir && chars[i] == sec) {
                deque.removeLast();
                result += score;
            } else {
                deque.addLast(chars[i]);
            }
        }
        char[] temp = new char[deque.size()];
        int i = 0;
        while (!deque.isEmpty()) {
            temp[i] = deque.removeFirst();
            i++;
        }
        return temp;
    }
}
