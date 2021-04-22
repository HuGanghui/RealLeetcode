package DynamicProgramming;

/**
 * 1824. 最少侧跳次数 Median
 * https://leetcode-cn.com/problems/minimum-sideway-jumps/
 *
 * 很明显，需要使用动态规划，并且是二维的，一维是第到第几个点，另一维是到当前点时需要的
 * 最少的侧跳数，但是要注意在有障碍的情况下，如何去更新迭代公式，侧跳不能是斜着跳，所以
 * 需要先沿着跑道获得沿着跑道更新后的dp[i][j]=dp[i-1][j]，然后再考虑是否从当前i的其他
 * j侧跳可以减少总侧跳次数。哎，还是有点复杂的，更新顺序很重要。。。
 * 时间复杂度O(n)
 *
 */
public class MinSideJumps {

    private final int MAX = (int)(5 * Math.pow(10, 5));

    public int minSideJumps(int[] obstacles) {
        int n = obstacles.length;
        if (n == 1) {
            return 0;
        }
        int[][] dp = new int[n][3];
        for(int i = 0; i < 3; i++) {
            dp[0][i] = 0;
        }
        dp[1][0] = 1;
        dp[1][1] = 0;
        dp[1][2] = 1;
        if (obstacles[1] != 0) {
            dp[1][obstacles[1]-1] = MAX;
        }

        for (int i = 2; i < n; i++) {

            dp[i][0] = dp[i-1][0];
            dp[i][1] = dp[i-1][1];
            dp[i][2] = dp[i-1][2];
            if (obstacles[i] != 0) {
                dp[i][obstacles[i]-1] = MAX;
            }
            if (obstacles[i]-1 != 0) {
                dp[i][0] = Math.min(dp[i][0], Math.min(dp[i][1]+1, dp[i][2]+1));
            }
            if (obstacles[i]-1 != 1) {
                dp[i][1] = Math.min(dp[i][0]+1, Math.min(dp[i][1], dp[i][2]+1));
            }
            if (obstacles[i]-1 != 2) {
                dp[i][2] = Math.min(dp[i][0]+1, Math.min(dp[i][1]+1, dp[i][2]));
            }
        }
        return Math.min(dp[n-1][0], Math.min(dp[n-1][1], dp[n-1][2]));
    }
}
