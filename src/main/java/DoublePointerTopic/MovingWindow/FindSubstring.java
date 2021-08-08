package DoublePointerTopic.MovingWindow;
import java.util.*;

/**
 * 30. 串联所有单词的子串 Hard
 * https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/
 *
 * 这题有一个关键点在于所有单词长度相等，因此就可以将单词长度l作为基本的滑动单位，只不过
 * 这样需要考虑l个不同的起点。其它的就是普通的滑动窗口。
 */
public class FindSubstring {
    public List<Integer> findSubstring(String s, String[] words) {
        int word_length = words[0].length();
        Map<String, Integer> need = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            need.put(words[i], need.getOrDefault(words[i], 0) + 1);
        }
        int total = words.length * word_length;
        Set<Integer> result = new HashSet<>();
        int n = s.length();
        for (int i = 0; i < word_length; i++) {
            int left = i;
            int right = i;
            Map<String, Integer> window = new HashMap<>();
            int valid = 0;
            while (right < n) {
                if (right + word_length > n) {
                    break;
                }
                String added = s.substring(right, right + word_length);
                right += word_length;
                if (need.containsKey(added)) {
                    window.put(added, window.getOrDefault(added, 0) + 1);
                    // 只有相等的时候才加去一次valid，不可多，不可少
                    if (window.get(added).equals(need.get(added))) {
                        valid++;
                    }
                }

                while (right - left == total) {
                    if (valid == need.size()) {
                        result.add(left);
                    }
                    String removed = s.substring(left, left + word_length);
                    left += word_length;
                    if (need.containsKey(removed)) {
                        // 只有相等的时候才减去一次valid，不可多，不可少
                        if (window.get(removed).equals(need.get(removed))) {
                            valid--;
                        }
                        window.put(removed, window.get(removed) - 1);
                    }
                }
            }
        }
        List<Integer> list = new ArrayList<>();
        for (Integer ele : result) {
            list.add(ele);
        }
        return list;
    }
}
