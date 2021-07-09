package DynamicProgramming.MinMax;

import java.util.Arrays;

/**
 * 322. 零钱兑换 Median
 * https://leetcode-cn.com/problems/coin-change/
 *
 * 相对比较入门的动态规划题，不难想到对应的状态转移方程
 *
 * 最值问题，与排列组合无关，因此可以有两种不同的状态转移方程。
 */
public class CoinChange {

//       穷举回溯的解法，会超时 复杂度 O(S^n) S为coins数量，n为每个coin在amount下可取的最大值
    private int result = -1;
    public int coinChangeDFS(int[] coins, int amount) {
        int size = 0;
        backtracking(0, size, coins, amount);
        return result;
    }

    private void backtracking(int startIndex, int size, int[] coins, int amount) {
        if (amount == 0) {
            if (result == -1 || size < result) {
                result = size;
            }
            return;
        }

        for (int i = startIndex; i < coins.length; i++) {
            if (result != -1 && size > result) {
                continue;
            }
            if (amount >= coins[i]) {
                size++;
                backtracking(i, size, coins, amount - coins[i]);
                size--;
            }
        }
    }

//     动态规划-自上而下，备忘录的形式
    public int coinChangeMemo(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }
        return coinChange(coins, amount, new int[amount]);
    }

    private int coinChange(int[] coins, int rem, int[] memo) {
        if (rem  < 0) {
            return -1;
        }

        if (rem == 0) {
            return 0;
        }

        if (memo[rem - 1] != 0) {
            return memo[rem - 1];
        }

        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, rem - coin, memo);
            if (res >= 0 && res < min) {
                min = 1 + res;
            }
        }
        memo[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return memo[rem - 1];
    }

    // 动态规划-自下而上
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        int max = amount + 1;
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    // 上面的DP都是根据排列的方式完成的，也可以通过组合的方式，因为
    // 求最大最小值，与排列或者组合的关系不大，因此两种都可以，不过这题排列写法
    // 更简单些。
    private final int INIT = Integer.MAX_VALUE - 10;

    public int coinChangeCombination(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n+1][amount+1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INIT);
        }

        for (int i=0; i <= n; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j - coins[i-1] >=0) {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j - coins[i-1]]+1);
                } else {
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j]);
                }
            }
        }

        return dp[n][amount] == (INIT) ? -1 : dp[n][amount];
    }
}
