package DynamicProgramming;

/**
 * 剑指 Offer 49. 丑数 Median
 * https://leetcode-cn.com/problems/chou-shu-lcof/
 *
 * 这题的递推方程本身我其实就没特别理解，强行记忆。
 */
public class NthUglyNumber {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; i++) {
            int num1 = dp[p2] * 2;
            int num2 = dp[p3] * 3;
            int num3 = dp[p5] * 5;
            dp[i] = Math.min(num1, Math.min(num2, num3));
            if (dp[i] == num1) {
                p2++;
            }
            if (dp[i] == num2) {
                p3++;
            }
            if (dp[i] == num3) {
                p5++;
            }
        }
        return dp[n];
    }
}
