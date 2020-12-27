package DynamicProgramming;

/**
 * 343. 整数拆分 Median
 * https://leetcode-cn.com/problems/integer-break/
 *
 * 这题很明显的可以使用DP算法：递推公式大体如下：
 * dp[i] = max(dp[i-m] * dp[i], dp[i]) 0<=m<=i;
 * 但是这题有个坑，就是一开始写base案例的时候，dp[2] 拆封结果应该是 1
 * 但是后续计算应该使用dp[2] = 2, dp[3]同样，因此这个初始base的设置也
 * 很重要，这时候自己能给测试就显得很重要了。
 */
public class IntegerBreak {
    public int integerBreak(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i-j] * dp[j], dp[i]);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        IntegerBreak integerBreak = new IntegerBreak();
        int result = integerBreak.integerBreak(10);
        System.out.println(result);
    }
}
