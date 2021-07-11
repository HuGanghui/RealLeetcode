package DynamicProgramming.Knapsack.MultiChoice;

import java.util.Arrays;

/**
 * 1641. 统计字典序元音字符串的数目 Median
 * https://leetcode-cn.com/problems/count-sorted-vowel-strings/
 *
 * 这里的在不同的长度位置，也是有多种选择，因此也像0-1背包的多种选择变种，只不过
 * 还有一个限制条件就是，选择是随着上一个选择有一定的限制的。
 */
public class CountVowelStrings {
    public int countVowelStrings(int n) {
        int m = 5;
        int[][] dp = new int[n+1][m+1];

        Arrays.fill(dp[1], 1);

        // 递推公式：dp[i][j] += dp[i-1][k] k <= j
        for (int i = 2; i <=n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int k = 1; k <= j; k++) {
                    dp[i][j] += dp[i-1][k];
                }
            }
        }

        int result = 0;
        for (int i = 1; i <=m; i++) {
            result += dp[n][i];
        }
        return result;
    }
}
