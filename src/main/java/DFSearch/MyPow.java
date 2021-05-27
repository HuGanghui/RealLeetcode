package DFSearch;

/**
 * 剑指 Offer 16. 数值的整数次方 Median
 * https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/
 *
 * 很明显可以通过DFS来减少时间复杂度，思想上其实很像DP，和Fib数列类似，虽然不是典型
 * 的DP问题，但是有递推公式，并且有重复子问题，因此这里的DFS也可以说是带memo的自顶向下。
 * 只不过这里没有显式的构建一个memo，而是知道重复计算何时出现，直接利用了。
 *
 * 另外，这里设计到负指数的问题，当然可以变成正指数来做，但是需要注意的是，有越界的可能，
 * 因为int范围是-2^31 <= n <= 2^31-1，所以需要考虑越界问题。
 */
public class MyPow {
    public double myPow(double x, int n) {
        return dfs(x, n);
    }

    private double dfs(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            if (n == Integer.MIN_VALUE) {
                return 1 / (x * dfs(x, Integer.MAX_VALUE));
            }
            return 1 / (dfs(x, -n));
        }
        if (n % 2 == 0) {
            double temp = dfs(x, n / 2);
            return temp * temp;
        } else {
            double temp = dfs(x, (n - 1) / 2);
            return temp * temp * x;
        }
    }
}
