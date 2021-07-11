package DynamicProgramming.Knapsack.MultiChoice;

/**
 * 1155. 掷骰子的N种方法 Median
 * https://leetcode-cn.com/problems/number-of-dice-rolls-with-target-sum/
 *
 * 类似0-1背包的多个选择的变体，因此就递推公式稍微变形一点就行，不过因为多个选择，
 * 因此写法可以变成三重循环。
 */
public class NumRollsToTarget {
    private final int MOD = (int) Math.pow(10, 9) + 7;

    public int numRollsToTarget(int d, int f, int target) {
        int n = d;
        int m = target;
        int[][] dp = new int[n+1][m+1];

        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j<=m; j++) {
                for (int k = 1; k<=f; k++) {
                    if (k <= j) {
                        dp[i][j] = (dp[i][j] % MOD + dp[i-1][j-k] % MOD) % MOD;
                    }
                }
            }
        }

        return dp[n][m];
    }
}
