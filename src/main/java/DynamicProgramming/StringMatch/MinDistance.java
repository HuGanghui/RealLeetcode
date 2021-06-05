package DynamicProgramming.StringMatch;

import java.util.Arrays;

/**
 * 72. 编辑距离 Hard
 * https://leetcode-cn.com/problems/edit-distance/
 *
 * 很典型的两个字符串匹配的题目，并且可以很容易联想到其子问题以及递推公式，
 * 因此利用动态规划来解决这个问题就显而易见了，那DP的关键就是递推公式（包括明确
 * 状态，选择），base case，以及使用memo的自顶向下或者自底向上。
 */
public class MinDistance {
    private int[][] memo;

    public int minDistance(String word1, String word2) {
        char[] word1chars = word1.toCharArray();
        char[] word2chars = word2.toCharArray();
        int n = word1chars.length;
        int m = word2chars.length;
        memo = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        int ans = minDistance(word1chars, word2chars, n-1, m-1);
        return ans;
    }

    private int minDistance(char[] word1, char[] word2, int i, int j) {
        int ans = 0;
        if (i < 0) {
            ans += (j + 1);
            return ans;
        }

        if (j < 0) {
            ans += (i + 1);
            return ans;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (word1[i] == word2[j]) {
            ans += minDistance(word1, word2, i-1, j-1);
        } else {
            // 插入
            ans += Math.min(Math.min(minDistance(word1, word2, i, j - 1) + 1,
            // 删除
            minDistance(word1, word2, i - 1, j) + 1),
            // 替换
            minDistance(word1, word2, i-1, j-1) + 1);
        }
        memo[i][j] = ans;
        return ans;
    }
}
