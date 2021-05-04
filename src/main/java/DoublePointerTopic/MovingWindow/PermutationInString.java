package DoublePointerTopic.MovingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 567. 字符串的排列 Median
 * https://leetcode-cn.com/problems/permutation-in-string/
 *
 * 相似的寻找匹配子串，利用滑动窗口模板很好的解决了。
 */
public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        int s_length = s2.length();
        int t_length = s1.length();
        for (int i = 0; i < t_length; i++) {
            char temp = s1.charAt(i);
            need.put(temp, need.getOrDefault(temp, 0) + 1);
        }
        int left = 0;
        int right = 0;
        int valid = 0;
        while(right < s_length) {
            char added = s2.charAt(right);
            right++;
            if (need.containsKey(added)) {
                window.put(added, window.getOrDefault(added, 0) + 1);
                if (window.get(added).equals(need.get(added))){
                    valid++;
                }
            }
            while(right - left == t_length) {
                if (valid == need.size()) {
                    return true;
                }
                char removed = s2.charAt(left);
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
        return false;
    }
}
