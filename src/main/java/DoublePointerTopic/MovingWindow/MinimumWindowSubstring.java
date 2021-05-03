package DoublePointerTopic.MovingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. 最小覆盖子串 Hard
 * https://leetcode-cn.com/problems/minimum-window-substring/
 *
 * 根据labuladong的滑动窗口总结，就是一个典型的字符串子串问题，直接利用模板就可以
 * 搞定，时间复杂度O(n)，但是有几个细节值得注意：
 * 1. 频繁使用s.length()，应当缓存下来
 * 2. Integer比较的时候，记得Integer是对象，Integer会缓存频繁使用的数值，
 * 数值范围为-128到127，在此范围内直接返回缓存值，超过该范围就会new一个对象。
 * 因此最后一个测试用例过不了就是因为超过了范围，== 却要求指向同一个对象，Integer的equals
 * 则重写了，比较的还是值。
 */
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        int s_length = s.length();
        int t_length = t.length();
        for (int i = 0; i < t_length; i++) {
            char temp = t.charAt(i);
            need.put(temp, need.getOrDefault(temp, 0) + 1);
        }
        int left = 0;
        int right = 0;
        int valid = 0;
        int start = 0;
        int len = Integer.MAX_VALUE;
        while(right < s_length) {
            char c = s.charAt(right);
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }

            while(valid == need.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                char removed = s.charAt(left);
                left++;
                if (need.containsKey(removed)) {
                    if (window.get(removed).equals(need.get(removed))) {
                        valid--;
                    }
                    window.replace(removed, window.get(removed)-1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}
