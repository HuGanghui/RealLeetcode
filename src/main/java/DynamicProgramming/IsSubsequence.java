package DynamicProgramming;

/**
 * 392. 判断子序列
 * https://leetcode-cn.com/problems/is-subsequence/
 *
 * 很简单的双指针做法，进阶的是动态规划
 * 但是要注意的是写法上的简洁性且不易出错，for-while混杂不如完全的while
 * 时间复杂度是O(m+n) 空间复杂度O(1)
 *
 * 进阶：
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，
 * 你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 *
 * 就是当有特别多的n，我们应当对T进行充分的预处理，可以预处理出对于
 * T的每一个位置，从该位置开始往后每一个字符第一次出现的位置，这样后续
 * 我们可以利用f数组，每次O(1)的跳转到下一个位置，直到位置变为m或s中的每一个字符都匹配成功
 *
 * 动态规划
 *
 * 空间换时间的概念
 */
public class IsSubsequence {
    // 双指针
    public boolean isSubsequence(String s, String t) {
        int n = s.length();
        int m = t.length();
        int i = 0;
        int j = 0;
        while(i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == n;
    }

    // 动态规划解法
    public boolean isSubsequenceDP(String s, String t) {
        int n = s.length(), m = t.length();

        int[][] f = new int[m + 1][26];
        for (int i = 0; i < 26; i++) {
            f[m][i] = m;
        }

        for (int i = m - 1; i >= 0; i--) { // 从下往上
            for (int j = 0; j < 26; j++) {
                if (t.charAt(i) == j + 'a')
                    f[i][j] = i;
                else
                    f[i][j] = f[i + 1][j];
            }
        }
        int add = 0;
        for (int i = 0; i < n; i++) {
            if (f[add][s.charAt(i) - 'a'] == m) {
                return false;
            }
            add = f[add][s.charAt(i) - 'a'] + 1;
        }
        return true;
    }

      // for-while 混杂
//    public boolean isSubsequence(String s, String t) {
//        if (s.length() == 0) {
//            return true;
//        }
//        int j = 0;
//        boolean result = false;
//        for (int i = 0; i < s.length(); i++) {
//            while(j < t.length()) {
//                if (i == s.length() -1 && s.charAt(i) == t.charAt(j)) {
//                    return true;
//                }
//                if (s.charAt(i) == t.charAt(j)) {
//                    j++;
//                    break;
//                }
//                j++;
//            }
//        }
//        return result;
//    }

    public static void main(String[] args) {
        IsSubsequence isSubsequence = new IsSubsequence();
        isSubsequence.isSubsequence("aaaaaa", "bbaaaa");
    }
}
