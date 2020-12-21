package DynamicProgramming;

/**
 * 509. 斐波那契数 Easy
 * https://leetcode-cn.com/problems/fibonacci-number/
 *
 * 一般求斐波那契，都是用递归，但会产生很多的重叠子问题，时间复杂度达到指数级别
 * 因此可以使用备忘录来优化穷举过程（动态规划-自顶向下），也可以使用动态规划-自低向上的
 * 方式，不过，自顶向上从递归（穷举）衍生而来，比较直观，当然穷举也要获得状态转移方程，这个
 * 获取的过程是最难的。
 */
public class FibonacciNumber {

    public int fib(int n) {
        int[] memo = new int[n+1]; // 备忘录
        return up2down(n, memo);
    }

    private int recursive(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        return recursive(n - 1) + recursive(n - 2);
    }

    private int up2down(int n, int[] memo) {
        if (n == 0 || n == 1) {
            return n;
        }

        if (memo[n] != 0) {
            return memo[n];
        }

        return up2down(n - 1, memo) + up2down(n - 2, memo);
    }

    private int down2up(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
