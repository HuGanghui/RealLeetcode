package HashMapTopic;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 50. 第一个只出现一次的字符 Easy
 * https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/
 *
 * 这题很容易想到利用哈希表来维护出现次数，不过值得注意的是，题目要求
 * 第一个出现一次的字符，因此在判断的时候的顺序需要是入哈希表的顺序，
 * 这个要求一方面可以让哈希表完成，当然更简单的，就是让chars来完成。
 *
 */
public class FirstUniqChar {
    public char firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        int n = chars.length;
        for(int i = 0; i < n; i++) {
            map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
        }
        char result = ' ';
        for (char key : chars) {
            if (map.get(key) == 1) {
                result = key;
                break;
            }
        }
        return result;
    }
}
