package DynamicProgramming.Knapsack.Complete.WithGraph;

import java.util.Arrays;

/**
 * 1928. 规定时间内到达终点的最小花费 Hard
 * https://leetcode-cn.com/problems/minimum-cost-to-reach-destination-in-time/
 * LeetcodeDoubleWeeklyMatch 56
 *
 * 这题转换一下，背包容量就是时间，然后可以重复选择的是城市，要求时间不大于最大背包容量的前提下的从城市0-n-1的最小花费。
 * 就相当于一个完全背包问题的变种。
 * 求最大最小，因此可以利用排列数来求，同时还多了一个状态便是城市，这样会结合图来限制一些选择。
 *
 * 同时，这里还有一个区别就是对于状态的设置，是恰好时间为t，而不是时间小于等于t，同时城市为n-1，所以最后的答案，还需要
 * 在所有的t且城市为n-1中遍历取最小值。
 *
 */
public class MinCost {
    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        // dp[i][time] = Min(dp[k][time-time[i]]) (k,i 两个城市之间有联系)
        int n = passingFees.length;
        int m = maxTime;
        int[][] dp = new int[m+1][n];

        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = passingFees[0];
        for (int j = 1; j <= m; j++) {
            for (int[] edge : edges) {
                if (j >= edge[2]) {
                    if (dp[j-edge[2]][edge[1]] != Integer.MAX_VALUE) {
                        dp[j][edge[0]] = Math.min(dp[j-edge[2]][edge[1]] + passingFees[edge[0]], dp[j][edge[0]]);
                    }
                    if (dp[j-edge[2]][edge[0]] != Integer.MAX_VALUE) {
                        dp[j][edge[1]] = Math.min(dp[j-edge[2]][edge[0]] + passingFees[edge[1]], dp[j][edge[1]]);
                    }
                }
            }
        }

        int result = -1;
        for (int i = 0; i <=m; i++) {
            if (result == -1 || result > dp[i][n-1]) {
                result = dp[i][n-1];
            }
        }

        return result == Integer.MAX_VALUE ? -1 : result;

    }
}
