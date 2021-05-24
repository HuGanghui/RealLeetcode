package DoublePointerTopic.FastSlowPointer;

/**
 * 5763. 哪种连续子字符串更长 Easy
 * https://leetcode-cn.com/problems/longer-contiguous-segments-of-ones-than-zeros/
 * LeetcodeWeeklyMatch 242
 *
 * 这题现实就是分别得到0，1的连续最大子字符串，然后比较大小就行，直观上就能想到利用快慢指针
 * 来获取连续最大的结果。 其实还可以扩展，不止0，1，有n个不同的字符都能比较。
 */
public class CheckZeroOnes {
    public boolean checkZeroOnes(String s) {
        int oneMaxLength = 0;
        int zeroMaxLength = 0;
        char[] chars = s.toCharArray();
        oneMaxLength = getMaxLength(chars, '1');
        zeroMaxLength = getMaxLength(chars, '0');
        return oneMaxLength > zeroMaxLength;
    }

    private int getMaxLength(char[] chars, char num) {
        int slow = 0;
        int fast = 0;
        int n = chars.length;
        int result = 0;
        while (fast < n) {
            if (chars[fast] == num) {
                fast++;
            } else {
                fast++;
                slow = fast;
            }
            int temp = fast - slow + 1;
            if (result < temp) {
                result = temp;
            }
        }
        return result;
    }
}
