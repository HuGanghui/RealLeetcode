package HashMapTopic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 5809. 长度为 3 的不同回文子序列 Median
 * https://leetcode-cn.com/problems/unique-length-3-palindromic-subsequences/
 * LeetcodeWeeklyMatch 249
 *
 * 最关键的一个观察应该是长度为3，因此我们只需要知道每个字符首尾的index，然后再for循环每个
 * 字符，利用哈希集合来去重，从而知道有多少个满足要求的结果。由于字符最多就26个，因此是O(N)
 * 的时间复杂度，我比赛是判断失误，以为是O(N^2)。。。
 */
public class CountPalindromicSubsequence {
    public int countPalindromicSubsequence(String s) {
        Map<Character, int[]> map = new HashMap<>();
        char[] chars = s.toCharArray();
        int n = chars.length;
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(chars[i])) {
                map.put(chars[i], new int[] {i,i});
            } else {
                map.get(chars[i])[1] = i;
            }
        }
        int result = 0;
        for (Character key : map.keySet()) {
            int[] start_end = map.get(key);
            Set<Character> set = new HashSet<>();
            for (int i = start_end[0] + 1; i < start_end[1]; i++) {
                set.add(chars[i]);
            }
            result += set.size();
        }

        return result;
    }
}
