package DoublePointerTopic.MovingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 438. 找到字符串中所有字母异位词 Median
 * https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
 *
 * 异构词只是排列的换个名字罢了，还是找子串，那当然可以滑动窗口了。
 */
public class FindAnagramsInString {
    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        int s_length = s.length();
        int t_length = p.length();
        for (int i = 0; i < t_length; i++) {
            char temp = p.charAt(i);
            need.put(temp, need.getOrDefault(temp, 0)+1);
        }
        int left = 0;
        int right = 0;
        int valid = 0;
        List<Integer> result = new ArrayList<>();
        while(right < s_length) {
            char added = s.charAt(right);
            right++;
            if (need.containsKey(added)) {
                window.put(added, window.getOrDefault(added, 0) + 1);
                if (window.get(added).equals(need.get(added))) {
                    valid++;
                }
            }

            while(right - left == t_length) {
                if (valid == need.size()) {
                    result.add(left);
                }
                char removed = s.charAt(left);
                left++;
                if (need.containsKey(removed)) {
                    if (window.containsKey(removed)) {
                        if (window.get(removed).equals(need.get(removed))) {
                            valid--;
                        }
                        window.replace(removed, window.get(removed) - 1);
                    }
                }
            }
        }
        return result;
    }
}
