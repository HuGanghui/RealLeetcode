package DoublePointerTopic.MovingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 30. 串联所有单词的子串 Hard
 * https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/
 *
 * 这题从题目看和之前找到匹配子串是非常相似的，因此很直观的尝试使用之前的滑动窗口的思路来完成。
 *
 * 思路一
 * 但是有个问题就是，如果以char为单位，无法保证单个word是连续的，因此一种做法是当vaild == need.size()时，
 * 再用一个辅助函数来协助以String为单位进行判断，确保word也是连续的。（可以这么做的核心条件是题目说每个单词
 * 的长度都是一致的）。
 *
 * 思路二 参考：https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/solution/duo-qi-dian-hua-dong-chuang-kou-by-yexis-bl51/
 * 另外一个思路则是一开始就将基本的单元换成String，然后每次移动一个单词的长度d，也是利用了长度一致这个关键条件，
 * 其他和滑动窗口的思想一样。但是涉及到一个多起点的问题，以0为起点，每次移动一个单词的长度，会错过 1，2，... d-1
 * 为起点，所以要多一个for循环来做。
 */
public class SubstringHavingAllWords {
    // 思路一
    public List<Integer> findSubstring(String s, String[] words) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        int s_length = s.length();
        int t_length = 0;
        for (int i = 0; i < words.length; i++) {
            char[] word_chars = words[i].toCharArray();
            t_length += word_chars.length;
            for (int j = 0; j < word_chars.length; j++) {
                need.put(word_chars[j], need.getOrDefault(word_chars[j], 0)+1);
            }
        }

        Map<String, Integer> stringMap = new HashMap<>();
        int word_length = words[0].length();
        for (int i = 0; i < words.length; i++) {
            stringMap.put(words[i], stringMap.getOrDefault(words[i], 0) + 1);
        }

        int left = 0;
        int right = 0;
        int vaild = 0;
        List<Integer> result = new ArrayList<>();
        while(right < s_length) {
            char added = s.charAt(right);
            right++;
            if (need.containsKey(added)) {
                window.put(added, window.getOrDefault(added, 0)+1);
                if (window.get(added).equals(need.get(added))) {
                    vaild++;
                }
            }

            while(right - left == t_length) {
                if (vaild == need.size()) {
                    if (help(s, left, right, stringMap, word_length, result)) {
                        result.add(left);
                    }
                }
                char removed = s.charAt(left);
                left++;
                if (need.containsKey(removed)) {
                    if (window.containsKey(removed)) {
                        if (window.get(removed).equals(need.get(removed))) {
                            vaild--;
                        }
                        window.replace(removed, window.get(removed)-1);
                    }
                }
            }
        }
        return result;
    }

    private boolean help(String s, int left, int right, Map<String, Integer> stringMap,
                         int word_length, List<Integer> result) {
        Map<String, Integer> window = new HashMap<>();
        int vaild = 0;
        for (int i = left; i < right; i=i+word_length) {
            String key = s.substring(i, i+word_length);
            if (stringMap.containsKey(key)) {
                window.put(key, window.getOrDefault(key, 0) + 1);
                if (window.get(key).equals(stringMap.get(key))) {
                    vaild++;
                }
            } else {
                return false;
            }
        }
        if (vaild == stringMap.size()) {
            return true;
        } else {
            return false;
        }
    }
}
