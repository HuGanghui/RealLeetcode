package HashMapTopic;

import java.util.HashMap;
import java.util.Map;

/**
 * 409. 最长回文串 Easy
 * https://leetcode-cn.com/problems/longest-palindrome/
 *
 * 正确理解题意，正常思路就是哈希，但不一定要用hashmap，有时数组其实也很好。
 */
public class LongestPalindrome {
    public int longestPalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (char ele : chars) {
            if (map.containsKey(ele)) {
                map.replace(ele, map.get(ele)+1);
            } else {
                map.put(ele, 1);
            }
        }
        int result = 0;
        boolean oddFlag = true;
        for(Integer num : map.values()) {
            if (num % 2 == 0) {
                result += num;
            } else {
                if (oddFlag == true) {
                    result += num;
                    oddFlag = false;
                } else {
                    result += num - 1;
                }
            }
        }
        return result;
    }
}
