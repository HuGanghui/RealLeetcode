package DynamicProgramming;

/**
 * 本题是2020年腾讯技术笔试题
 *
 * 题目：
 * 由于工作业绩优秀，公司给小Q放了n天的假，身为工作狂的小Q打算在假期中工作、锻炼和休息。他有个奇怪的习惯：不会连续两天工作或锻炼。
 * 只有当公司营业时，小Q才能去工作，只有当健身房营业时，小Q才能去健身，小Q一天只能干一件事。给出假期中公司，
 * 健身房的营业情况，求小Q最小需要休息几天？
 *
 * 输入:
 * 第一行表示放假天数
 * 第二行n个数，0或1，表示公司第i天是否营业
 * 第三行表示健身房
 * 1为营业 0为不营业
 *
 * 输出：
 * 表示小Q休息的最少天数
 *
 * 参考博客：https://blog.csdn.net/zycxnanwang/article/details/101878823
 *
 * 这题其实和最小交换还是很相似的，都是多了一个状态，然后选择和状态有关并且有一定的限制条件，处理好这些，就能够
 * 理清楚递推公式，解决问题也就没有多困难了。
 */
public class MinRest {
    public int getMinRest() {
        int n = 10;
        int[] A = new int[n];
        int[] B = new int[n];
        int[][] dp = new int[n][3];
        dp[0][0] = 1;
        if (A[0] == 1) {
            dp[0][1] = 0;
        } else {
            dp[0][1] = 1;
        }
        if (B[0] == 1) {
            dp[0][2] = 0;
        } else {
            dp[0][2] = 1;
        }
        for (int i = 1; i < n; i++) {

            dp[i][0] = Math.min(dp[i-1][0], Math.min(dp[i-1][1], dp[i-1][2])) + 1;

            if (A[0] == 1) {
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]);
            } else {
                dp[i][1] = dp[i][0];
            }

            if (B[0] == 1) {
                dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]);
            } else {
                dp[i][2] = dp[i][0];
            }
        }
        int result = Math.min(dp[n-1][0], Math.min(dp[n-1][1], dp[n-1][2]));
        return result;
    }
}
