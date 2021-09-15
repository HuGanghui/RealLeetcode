package StringTopic;

/**
 * 1209. 删除字符串中的所有相邻重复项 II Median
 * https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string-ii/
 *
 * 通过一个辅助数组记录重复数量，当达到k就进行删除。
 */
public class RemoveNearDuplicatesII {
    public String removeDuplicates(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        // 记录连续重复数量
        int[] count = new int[sb.length()];
        for (int i = 0; i < sb.length(); i++) {
            if (i == 0 || sb.charAt(i) != sb.charAt(i-1)) {
                count[i] = 1;
            } else {
                count[i] = count[i-1] + 1;
                if (count[i] == k) {
                    // 借助StringBuilder来删除元素
                    sb.delete(i - k + 1, i + 1);
                    i = i - k;
                }
            }
        }
        return sb.toString();
    }
}
