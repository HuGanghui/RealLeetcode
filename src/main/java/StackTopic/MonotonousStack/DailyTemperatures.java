package StackTopic.MonotonousStack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 739. 每日温度 Median
 * https://leetcode-cn.com/problems/daily-temperatures/
 *
 * 这题就是比较明显的单调栈的应用了，就是要找到后面第一个的更大值
 * 的变形。
 */
public class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Pair> deque = new LinkedList<>();
        int n = temperatures.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            while(!deque.isEmpty() && deque.peekLast().temp < temperatures[i]) {
                Pair pair = deque.removeLast();
                result[pair.index] = i - pair.index;
            }
            deque.addLast(new Pair(i, temperatures[i]));
        }
        return result;
    }

    private class Pair {
        public int index;
        public int temp;
        public Pair(int index, int temp) {
            this.index = index;
            this.temp = temp;
        }
    }

}
