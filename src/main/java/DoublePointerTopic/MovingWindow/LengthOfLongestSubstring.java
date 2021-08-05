package DoublePointerTopic.MovingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 3. 无重复字符的最长子串 Median
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 *
 * 这题就比较有新意了，并不是两个子串找匹配的，而是一个串找无重复的最长子串，但其实还是可以利用滑动窗口，
 * 只不过内部缩减的条件变成了当发现某个重复了。
 */
public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> need = new HashMap<>();
        int s_length = s.length();
        int left = 0;
        int right = 0;
        int len = 0;
        while(right < s_length) {
            char added = s.charAt(right);
            right++;
            need.put(added, need.getOrDefault(added, 0) + 1);

            if (need.get(added).equals(1)) {
                len = Math.max(len, right - left);
            }

            while(need.get(added) > 1) {
                char removed = s.charAt(left);
                left++;
                need.replace(removed, need.get(removed)-1);
            }
        }
        return len;
    }
}
