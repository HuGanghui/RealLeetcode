package StackTopic.NestedDecoding;

import java.util.*;

/**
 * 394. 字符串解码 Median
 * https://leetcode-cn.com/problems/decode-string/
 *
 * 类似"3[a2[c]]" 这样的编码，墨奇科技一面算法题也就是这个，
 * 我的第一直觉就是从后往前遍历，遇到'['进行处理，判断前面
 * 的数字time是多少，然后将目前stack中知道']'的字符取出，重复time次，
 * 放入栈中；其它的字符就直接入栈。这样就可以解决嵌套的问题。
 *
 * 相比之下，墨奇的那题区别就是前面的数字一定2-9，而力扣这题可以更大，
 * 因此就需要循环处理一下。
 */
public class DecodeString {
    public String decodeString(String s) {
        int n = s.length();
        Stack<Character> stack = new Stack<>();
        for (int i = n-1; i >=0; i--) {
            char cur = s.charAt(i);
            if (cur == '[') {
                StringBuilder timeString = new StringBuilder();
                while (i - 1 >= 0 && isDigits(s.charAt(i-1))) {
                    timeString.insert(0, getDigits(s.charAt(i-1)));
                    i--;
                }
                int time = Integer.parseInt(timeString.toString());
                List<Character> list = new ArrayList<>();
                while (!stack.isEmpty() && stack.peek() != ']') {
                    list.add(stack.pop());
                }
                stack.pop();
                for (int j = 0; j < time; j++) {
                    for (int k = list.size() - 1; k >= 0; k--) {
                        stack.push(list.get(k));
                    }
                }
            } else {
                stack.push(cur);
            }
        }
        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }
        return builder.toString();
    }

    private int getDigits(char x) {
        return x - '0';
    }

    private boolean isDigits(char x) {
        if (x - '0' >= 0 && x - '0' <= 9) {
            return true;
        }
        return false;
    }
}
