package DynamicProgramming;

import java.util.Arrays;

/**
 * 322. 零钱兑换 Median
 * https://leetcode-cn.com/problems/coin-change/
 *
 * 相对比较入门的动态规划题，不难想到对应的状态转移方程
 */
public class CoinChange {

//       穷举回溯的解法，会超时 复杂度 O(S^n) S为coins数量，n为每个coin在amount下可取的最大值
//    private int result = -1;
//    public int coinChange(int[] coins, int amount) {
//        int size = 0;
//        backtracking(0, size, coins, amount);
//        return result;
//    }
//
//    private void backtracking(int startIndex, int size, int[] coins, int amount) {
//        if (amount == 0) {
//            if (result == -1 || size < result) {
//                result = size;
//            }
//            return;
//        }
//
//        for (int i = startIndex; i < coins.length; i++) {
//            if (result != -1 && size > result) {
//                continue;
//            }
//            if (amount >= coins[i]) {
//                size++;
//                backtracking(i, size, coins, amount - coins[i]);
//                size--;
//            }
//        }
//    }

//     动态规划-自上而下，备忘录的形式
//    public int coinChange(int[] coins, int amount) {
//        if (amount < 1) {
//            return 0;
//        }
//        return coinChange(coins, amount, new int[amount]);
//    }
//
//    private int coinChange(int[] coins, int rem, int[] count) {
//        if (rem  < 0) {
//            return -1;
//        }
//
//        if (rem == 0) {
//            return 0;
//        }
//
//        if (count[rem - 1] != 0) {
//            return count[rem - 1];
//        }
//
//        int min = Integer.MAX_VALUE;
//        for (int coin : coins) {
//            int res = coinChange(coins, rem - coin, count);
//            if (res >= 0 && res < min) {
//                min = 1 + res;
//            }
//        }
//        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
//        return count[rem - 1];
//    }

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


    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        coinChange.coinChange(new int[]{2}, 3);
    }
}
