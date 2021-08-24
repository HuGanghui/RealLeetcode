package DynamicProgramming.StringMatch;

/**
 * 44. 通配符匹配 Hard
 * https://leetcode-cn.com/problems/wildcard-matching/
 *
 * 类似正则表达式的做法，自底向上的DP按步骤完成即可。
 */
public class isMatchIII {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;

        for (int j = 1; j <= n; j++) {
            // 非'*'就肯定无法匹配了
            if (p.charAt(j-1) == '*') {
                dp[0][j] = true;
            } else {
                break;
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j-1) == '*') {
                    // 匹配多个 || 匹配一个 || 匹配零个
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-1] || dp[i][j-1];
                } else {
                    if (matches(s, p, i, j)) {
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
            }
        }

        return dp[m][n];
    }

    private boolean matches(String s, String p, int i, int j) {
        if (p.charAt(j-1) == '?') {
            return true;
        }
        return s.charAt(i-1) == p.charAt(j-1);
    }
}
