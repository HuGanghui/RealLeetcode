package DoublePointerTopic.FastSlowPointer;

/**
 * 1047. 删除字符串中的所有相邻重复项 Easy
 * https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string/
 * 
 * 本题使用Stack会很直观，时间和空间复杂度都是O(n)；
 * 更巧妙的解法是使用双指针，slow代表符合条件的元素的结尾+1，这样可以不需要额外的
 * 空间复杂度。
 */
public class removeNearDuplicates {
    public String removeDuplicates(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int slow = 0;
        int fast = 0;
        while (fast < n) {
            if (slow > 0 && chars[slow-1] == chars[fast]) {
                slow--;
            } else {
                chars[slow++] = chars[fast];
            }
            fast++;
        }
        StringBuilder builder = new StringBuilder();
        // slow为结尾
        for (int i = 0; i < slow; i++) {
            builder.append(chars[i]);
        }
        return builder.toString();
    }
}
