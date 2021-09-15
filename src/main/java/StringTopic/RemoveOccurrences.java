package StringTopic;

import java.util.*;

/**
 * 1910. 删除一个字符串中所有出现的给定子字符串 Median
 * https://leetcode-cn.com/problems/remove-all-occurrences-of-a-substring/
 *
 * 暴力就是依次判断每个的最后m位是否符合，符合则删除。
 * 优化方法是KMP，暂时不会。
 */
public class RemoveOccurrences {
    public String removeOccurrences(String s, String part) {
        char[] char_s = s.toCharArray();
        int n = char_s.length;
        char[] char_part = part.toCharArray();
        int m = char_part.length;
        Stack<Character> stack = new Stack<>();
        char[] temp = new char[m];
        for (int i = 0; i < char_s.length; i++) {
            stack.push(char_s[i]);
            // 依次判断每个最后m位
            if (stack.size() >= m) {
                for (int j = m - 1; j >=0; j--) {
                    temp[j] = stack.pop();
                }
                boolean match = true;
                for (int j = m - 1; j >= 0; j--) {
                    if (temp[j] != char_part[j]) {
                        match = false;
                        break;
                    }
                }
                if (!match) {
                    for (int j = 0; j < m; j++) {
                        stack.push(temp[j]);
                    }
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }
        return builder.reverse().toString();
    }
}
