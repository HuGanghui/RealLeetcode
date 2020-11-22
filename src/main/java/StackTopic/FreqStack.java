package StackTopic;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 895. 最大频率栈 Hard
 * https://leetcode-cn.com/problems/maximum-frequency-stack/
 *
 * 让我给想出来了，厉害，思路完全和标准答案一模一样 (但是标准答案更简洁，因为使用了一些map的高级API)
 * 核心有使用哈希表来映射当前元素的数量，并且不同频率维护不同的栈，需要的就是最大频率most的栈中的栈顶元素。
 */
public class FreqStack {
    Map<Integer, Integer> freqMap = new HashMap<>();
    Map<Integer, Stack<Integer>> listMap = new HashMap<>();
    int mostFreq;

    public FreqStack() {
        mostFreq = 0;
    }

    public void push(int x) {
        Integer freq = freqMap.get(x);
        if (freq != null) {
            int newFreq = freq + 1;
            freqMap.put(x, newFreq);
            Stack<Integer> stack = listMap.get(newFreq);
            if (newFreq > mostFreq) {
                mostFreq = newFreq;
            }
            if (stack != null) {
                stack.push(x);
            } else {
                stack = new Stack<>();
                stack.push(x);
                listMap.put(newFreq, stack);
            }
        } else {
            freqMap.put(x, 1);
            Stack<Integer> stack = listMap.get(1);
            if (1 > mostFreq) {
                mostFreq = 1;
            }
            if (stack != null) {
                stack.push(x);
            } else {
                stack = new Stack<>();
                stack.push(x);
                listMap.put(1, stack);
            }
        }
    }

    public int pop() {
        Stack<Integer> mostFreqStack = listMap.get(mostFreq);
        int result = mostFreqStack.pop();
        int newFreq = freqMap.get(result);
        if (newFreq >= 1) {
            freqMap.put(result, newFreq - 1);
        }
        if (mostFreqStack.isEmpty()) {
            mostFreq--;
        }
        return result;

    }

    public static void main(String[] args) {
        /*
         * ["FreqStack","push","push","push","push","push","push","pop","push","pop","push","pop","push","pop","push","pop","pop","pop","pop","pop","pop"]
         * [[],[4],[0],[9],[3],[4],[2],[],[6],[],[1],[],[1],[],[4],[],[],[],[],[],[]]
         */
        FreqStack freqStack = new FreqStack();
        freqStack.push(4);
        freqStack.push(0);
        freqStack.push(9);
        freqStack.push(3);
        freqStack.push(4);
        freqStack.push(2);
        freqStack.pop();
        freqStack.push(6);
        freqStack.pop();
        freqStack.push(1);
        freqStack.pop();
        freqStack.push(1);
        freqStack.pop();
        freqStack.push(4);
        freqStack.pop();
        freqStack.pop();
        freqStack.pop();
        freqStack.pop();
        freqStack.pop();
        freqStack.pop();
    }
}
