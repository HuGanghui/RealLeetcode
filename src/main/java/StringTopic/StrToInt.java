package StringTopic;

/**
 * 剑指 Offer 67. 把字符串转换成整数 Median
 * https://leetcode-cn.com/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof/
 *
 * 参考最高分题解，核心在于对首个字符的处理，如果是空格就循环丢弃，然后到第一个非空格，如果是正号
 * 或者负号，设置一个符号标记，然后遍历接下来的，如果是非数字则跳出，返回result；否则进行ascii码
 * 转换为数字的过程，先乘10，在加。
 *
 * 但是要主要有个越界的情况，就是大于int所能表示的最大和最小，这个判断利用 bndry = Integer.MAX_VALUE / 10；
 * 如果 res > bndry || res == bndry && str.charAt(j) > '7' 则是越界了，通过符号标记判断返回最大还是最小。
 */
public class StrToInt {
    public int strToInt(String str) {
        int res = 0;
        int bndry = Integer.MAX_VALUE / 10;
        int i = 0;
        int sign = 1;
        int length = str.length();
        if (length == 0) {
            return 0;
        }
        while (str.charAt(i) == ' ')
            if (++i == length) return 0;
        if (str.charAt(i) == '-') {
            sign = -1;
        }
        if (str.charAt(i) == '-' || str.charAt(i) == '+') {
            i++;
        }
        for (int j = i; j < length; j++) {
            if (str.charAt(j) < '0' || str.charAt(j) > '9') {
                break;
            }
            if (res > bndry || res == bndry && str.charAt(j) > '7') {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = res * 10 + (str.charAt(j) - '0');
        }
        return sign * res;
    }
}
