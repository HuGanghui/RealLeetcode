package DynamicProgramming;

/**
 * 516. 最长回文子序列 Median
 *
 * https://leetcode-cn.com/problems/longest-palindromic-subsequence/
 *
 * 参考解答：
 * https://leetcode-cn.com/problems/longest-palindromic-subsequence/solution/a-fei-xue-suan-fa-zhi-si-ke-yi-dao-ti-516-zui-chan/
 * https://leetcode-cn.com/problems/longest-palindromic-subsequence/solution/zi-xu-lie-wen-ti-tong-yong-si-lu-zui-chang-hui-wen/
 *
 * 一般涉及到两个字符串/数组的子序列或者一个字符串/数组但是区间概念很重要比如回文子序列的时候，就会定义dp[i][j]，
 * 然后按情况考虑出递推方程就行。
 */
public class LongestPalindromeSubseq {
    // DP方法，O(n^2)
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        String[] strings = s.split("");
        int[][] dp = new int[n][n];
        for (int l = 0; l < n; l++) {
            for (int i = 0; i + l < n; i++) {
                int j = i + l;
                if (l == 0) {
                    dp[i][j] = 1;
                } else {
                    if (strings[i].equals(strings[j])) {
                        dp[i][j] = dp[i+1][j-1] + 2;
                    } else {
                        dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                    }
                }
            }
        }
        return dp[0][n-1];
    }

    // 暴力枚举，加个memo备忘录就是自顶向下的DP
    public int longestPalindromeSubseq2(String s) {
        return helper(s, 0, s.length() - 1);
    }

    private int helper(String s, int start, int end) {
        if (start == end) return 1;
        if (start > end) return 0;
        int ans = 0;
        if (s.charAt(start) == s.charAt(end)) {
            ans = helper(s, start + 1, end - 1) + 2;
        } else {
            ans = Math.max(helper(s, start + 1, end),
                    helper(s, start, end - 1));
        }
        return ans;
    }

}
